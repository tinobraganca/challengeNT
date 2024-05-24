package com.ntconsulting.hotel.userservice.service.impl;

import com.ntconsulting.hotel.userservice.enumaration.PaymentStatusEnum;
import com.ntconsulting.hotel.userservice.repository.PaymentRepository;
import com.ntconsulting.hotel.userservice.repository.entity.PaymentEntity;
import com.ntconsulting.hotel.userservice.service.PaymentService;
import com.ntconsulting.hotel.userservice.web.rest.util.error.ApiError;
import com.ntconsulting.hotel.userservice.web.rest.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentEntity makePayment(PaymentEntity payment){
        return paymentRepository.save(payment);
    }

    @Override
    public Boolean isApproved(PaymentEntity payment) {
        Optional<PaymentEntity> pay = paymentRepository.findById(payment.getId());
        if(pay.isPresent()){
            if(pay.get().getStatus().equals(PaymentStatusEnum.PAYMENT_APPROVED.getMessage())){
                return Boolean.TRUE;
            }else {
                return Boolean.FALSE;
            }
        }
        throw new NotFoundException(new ApiError(LocalDateTime.now().toString(), HttpStatus.NOT_FOUND,
                4004, "Error pagamento nao encontrado"));
    }

}
