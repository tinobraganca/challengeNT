package com.ntconsulting.hotel.userservice.repository;

import com.ntconsulting.hotel.userservice.repository.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
