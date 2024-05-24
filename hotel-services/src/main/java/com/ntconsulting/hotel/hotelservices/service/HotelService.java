package com.ntconsulting.hotel.hotelservices.service;

import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    List<HotelDTO> createHotel(List<HotelDTO> hotel);

    List<HotelDTO> findAll();

    Optional<HotelEntity> findById(Long id);
}
