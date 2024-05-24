package com.ntconsulting.hotel.userservice.service.impl;

import com.ntconsulting.hotel.userservice.async.producer.SQSBookingHotelNotificationProducer;
import com.ntconsulting.hotel.userservice.async.producer.SQSBookingUserNotificationProducer;
import com.ntconsulting.hotel.userservice.async.producer.SQSPaymentHotelNotificationProducer;
import com.ntconsulting.hotel.userservice.async.producer.SQSPaymentUserNotificationProducer;
import com.ntconsulting.hotel.userservice.dto.RequestBookingDTO;
import com.ntconsulting.hotel.userservice.enumaration.PaymentStatusEnum;
import com.ntconsulting.hotel.userservice.repository.BookingRepository;
import com.ntconsulting.hotel.userservice.repository.entity.*;
import com.ntconsulting.hotel.userservice.service.BookingService;
import com.ntconsulting.hotel.userservice.service.PaymentService;
import com.ntconsulting.hotel.userservice.service.RoomService;
import com.ntconsulting.hotel.userservice.service.UserService;
import com.ntconsulting.hotel.userservice.web.rest.util.error.ApiError;
import com.ntconsulting.hotel.userservice.web.rest.util.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final UserService userService;

    private final SQSPaymentUserNotificationProducer sqsPaymentUserNotificationProducer;
    private final SQSPaymentHotelNotificationProducer sqsPaymenthotelNotificationProducer;
    private final SQSBookingUserNotificationProducer sqsBookingUserNotificationProducer;
    private final SQSBookingHotelNotificationProducer sqsBookingHotelNotificationProducer;


    private final RoomService roomService;

    private final PaymentService paymentService;

    private final BookingRepository bookingRepository;

    private static final String MSG_PAYMENT_USER_APPROVED = "Seu pagamento acaba de ser APROVADO!, meio de pagamento: ";
    private static final String MSG_PAYMENT_USER_NOT_APPROVED = "Seu pagamento acaba de ser NAO APROVADO!, meio de pagamento: ";
    private static final String MSG_PAYMENT_HOTEL_APPROVED = "Pagamento acaba de ser APROVADO!, para quarto: ";


    private final static Logger LOG = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    public BookingServiceImpl(UserService userService, SQSPaymentUserNotificationProducer sqsPaymentUserNotificationProducer, SQSPaymentHotelNotificationProducer sqsPaymenthotelNotificationProducer, SQSBookingUserNotificationProducer sqsBookingUserNotificationProducer, SQSBookingHotelNotificationProducer sqsBookingHotelNotificationProducer, RoomService roomService,
                              PaymentService paymentService, BookingRepository bookingRepository){
        this.userService = userService;
        this.sqsPaymentUserNotificationProducer = sqsPaymentUserNotificationProducer;
        this.sqsPaymenthotelNotificationProducer = sqsPaymenthotelNotificationProducer;
        this.sqsBookingUserNotificationProducer = sqsBookingUserNotificationProducer;
        this.sqsBookingHotelNotificationProducer = sqsBookingHotelNotificationProducer;
        this.roomService = roomService;
        this.paymentService = paymentService;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void createBooking(RequestBookingDTO requestBooking) {
        LOG.info("M=BookingService.createBooking status=start method=createBooking user={} room={}", requestBooking.idUser(), requestBooking.idRoom());
        RoomEntity room = this.checkIfRoomStillAvalable(requestBooking);
        UserEntity user = this.checkUser(requestBooking);
        PaymentEntity payment = this.createPayment(user.getWalletEntity());
        this.saveBooking(requestBooking, room, user, payment);
        LOG.info("M=BookingService.createBooking status=end method=createBooking user={} room={}", requestBooking.idUser(), requestBooking.idRoom());
    }

    private UserEntity checkUser(RequestBookingDTO requestBooking) {
        LOG.info("M=BookingService.createBooking status=start method=checkUser user={} room={}", requestBooking.idUser(), requestBooking.idRoom());
        Optional<UserEntity> user = userService.findUser(requestBooking.idUser());
        return user.orElseThrow(() -> {
            LOG.error("M=BookingService.createBooking status=process method=checkUser user={} room={}," +
                    " User notFound", requestBooking.idUser(), requestBooking.idRoom());
            return new NotFoundException(new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND, 4004, "Error usuario nao encontrado"));
        });

    }

    private void saveBooking(RequestBookingDTO requestBooking, RoomEntity room, UserEntity user, PaymentEntity payment) {
        LOG.info("M=BookingService.createBooking status=start method=saveBooking user={} room={}", requestBooking.idUser(), requestBooking.idRoom());
        if(paymentService.isApproved(payment)){
            sqsPaymentUserNotificationProducer.sendPaymentStatus(MSG_PAYMENT_USER_APPROVED + user.getWalletEntity().getName());
            sqsBookingHotelNotificationProducer.sendStatus(MSG_PAYMENT_HOTEL_APPROVED + room.getName());
            BookingEntity booking = createBookingEntity(requestBooking, room, user, payment);
            bookingRepository.save(booking);
            sqsBookingUserNotificationProducer.sendStatus("Booking realizado" + user.getFirstName());
            sqsBookingHotelNotificationProducer.sendStatus("Booking realizado" + user.getFirstName());
        }else{
            LOG.error("M=BookingService.createBooking status=start method=saveBooking user={} room={}", requestBooking.idUser(), requestBooking.idRoom());
            sqsPaymentUserNotificationProducer.sendPaymentStatus(MSG_PAYMENT_USER_NOT_APPROVED + user.getWalletEntity().getName());
            sqsBookingUserNotificationProducer.sendStatus("Nao foi possivel realizar o Booking" + user.getFirstName());
        }
    }

    private BookingEntity createBookingEntity(RequestBookingDTO requestBooking, RoomEntity room, UserEntity user, PaymentEntity payment) {
        return new BookingEntity(requestBooking, room, user, payment);

    }

    private RoomEntity checkIfRoomStillAvalable(RequestBookingDTO requestBooking) {
        LOG.info("M=BookingService.createBooking status=process method=checkIfRoomStillAvalable user={} room={}", requestBooking.idUser(), requestBooking.idRoom());
        Optional<RoomEntity> room = roomService.findAvailable(requestBooking);
        return room.orElseThrow(() -> {
            LOG.error("M=BookingService.createBooking status=process method=checkIfRoomStillAvalable user={} room={}," +
                    " room unavailable", requestBooking.idUser(), requestBooking.idRoom());
            return new NotFoundException(new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND,
                    4004, "Error na alocacao do quarto enviado"));
        });
    }

    private PaymentEntity createPayment(WalletEntity userWallet) {
            //send to paymentService to makePayment
            //feignClient for paymentService
        LOG.info("M=BookingService.createBooking status=process method=createPayment");
        return paymentService.makePayment(new PaymentEntity("PAYMENT FOR BOOKING" + userWallet.getName(), userWallet.getType(), PaymentStatusEnum.PAYMENT_APPROVED));

    }
}
