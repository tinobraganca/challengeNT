package com.ntconsulting.hotel.searchservice.dto;

import com.ntconsulting.hotel.searchservice.repository.entity.HotelEntity;

public record HotelDTO(Long id, String name, String country, String city, String address, Integer rating, Long maxRoom) {
    public HotelDTO(HotelEntity hotelEntity){
        this(hotelEntity.getId() ,hotelEntity.getName(), hotelEntity.getCountry(),
                hotelEntity.getCity(), hotelEntity.getAddress(), hotelEntity.getRating(), hotelEntity.getMaxRoom());
    }


}
