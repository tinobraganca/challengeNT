package com.ntconsulting.hotel.userservice.service;

import com.ntconsulting.hotel.userservice.dto.RequestBookingDTO;
import com.ntconsulting.hotel.userservice.repository.entity.RoomEntity;

import java.util.Optional;

public interface RoomService {
    Optional<RoomEntity> findAvailable(RequestBookingDTO request);
}
