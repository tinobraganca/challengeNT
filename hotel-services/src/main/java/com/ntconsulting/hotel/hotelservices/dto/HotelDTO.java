package com.ntconsulting.hotel.hotelservices.dto;

import com.ntconsulting.hotel.hotelservices.repository.entity.HotelEntity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public record HotelDTO(Long id, String name, String country, String city, String address, Integer rating, Long maxRoom, List<RoomDTO> rooms) {
    public HotelDTO(HotelEntity hotelEntity){
        this(hotelEntity.getId() ,hotelEntity.getName(), hotelEntity.getCountry(),
                hotelEntity.getCity(), hotelEntity.getAddress(), hotelEntity.getRating(), hotelEntity.getMaxRoom(), hotelEntity.getRooms() != null ?
                        hotelEntity.getRooms().parallelStream().map(RoomDTO::new).collect(toList()) : null );
    }

}
