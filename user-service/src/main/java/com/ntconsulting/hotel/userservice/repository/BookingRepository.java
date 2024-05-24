package com.ntconsulting.hotel.userservice.repository;

import com.ntconsulting.hotel.userservice.repository.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
