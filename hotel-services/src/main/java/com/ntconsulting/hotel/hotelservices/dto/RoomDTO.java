package com.ntconsulting.hotel.hotelservices.dto;

import com.ntconsulting.hotel.hotelservices.repository.entity.RoomEntity;

import java.math.BigDecimal;

public record RoomDTO(Long id, String name, Integer peopleCapacity, BigDecimal price, Long hotelId, String hotelName, String hasBooking ,String createdAt, String updateAt) {
    public RoomDTO(RoomEntity roomEntity){
        this(roomEntity.getId() ,roomEntity.getName(), roomEntity.getPeopleCapacity(), roomEntity.getPrice(),
                roomEntity.getHotel().getId(), roomEntity.getHotel().getName(), roomEntity.getBooking() != null ? "true" : "false", roomEntity.getCreatedAt().toString(),
                roomEntity.getUpdateAt() != null ? roomEntity.getUpdateAt().toString() : null);
    }

     public RoomDTO {
        name = name;


    }
}
