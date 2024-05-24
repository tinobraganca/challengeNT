package com.ntconsulting.hotel.userservice.dto;



import com.ntconsulting.hotel.userservice.repository.entity.RoomEntity;

import java.math.BigDecimal;

public record RoomDTO(Long id, String name, Integer peopleCapacity, BigDecimal price, HotelDTO hotel, String hasBooking , String createdAt, String updateAt) {
    public RoomDTO(RoomEntity roomEntity){
        this(roomEntity.getId() ,roomEntity.getName(), roomEntity.getPeopleCapacity(), roomEntity.getPrice(),
                new HotelDTO(roomEntity.getHotel()), roomEntity.getBooking() != null ? "true" : "false", roomEntity.getCreatedAt().toString(),
                roomEntity.getUpdateAt() != null ? roomEntity.getUpdateAt().toString() : null);
    }
}
