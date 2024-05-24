package com.ntconsulting.hotel.searchservice.repository;


import com.ntconsulting.hotel.searchservice.repository.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
}
