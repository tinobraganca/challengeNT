package com.ntconsulting.hotel.hotelservices.repository;

import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
}
