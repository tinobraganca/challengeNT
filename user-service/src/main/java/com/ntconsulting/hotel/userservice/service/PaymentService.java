package com.ntconsulting.hotel.userservice.service;

import com.ntconsulting.hotel.userservice.repository.entity.PaymentEntity;

public interface PaymentService {
    PaymentEntity makePayment(PaymentEntity payment);

    Boolean isApproved(PaymentEntity payment);
}
