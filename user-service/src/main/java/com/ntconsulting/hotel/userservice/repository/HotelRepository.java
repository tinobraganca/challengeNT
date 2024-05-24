package com.ntconsulting.hotel.userservice.repository;


import com.ntconsulting.hotel.userservice.repository.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
}
