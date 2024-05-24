package com.ntconsulting.hotel.userservice.web.rest;

import com.ntconsulting.hotel.userservice.async.producer.SQSPaymentUserNotificationProducer;
import com.ntconsulting.hotel.userservice.dto.RequestBookingDTO;
import com.ntconsulting.hotel.userservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingRest {

    private final BookingService bookingService;

    @Autowired
    public BookingRest(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping(value = "save")
    public ResponseEntity<Object> makeBooking(@RequestBody RequestBookingDTO requestBooking){
        bookingService.createBooking(requestBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

}
