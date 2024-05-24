package com.ntconsulting.hotel.userservice.dto;

public record RequestBookingDTO(Long idRoom, Long idUser, String name, String startAt, String endAt, Integer countPeople) {
}
