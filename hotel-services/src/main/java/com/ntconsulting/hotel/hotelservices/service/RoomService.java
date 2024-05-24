package com.ntconsulting.hotel.hotelservices.service;

import com.ntconsulting.hotel.hotelservices.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createRoom(RoomDTO room);

    List<RoomDTO> createRooms(List<RoomDTO> rooms);

    List<RoomDTO> findAll();
}
