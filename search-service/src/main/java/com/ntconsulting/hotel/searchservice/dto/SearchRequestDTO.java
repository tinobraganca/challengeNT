package com.ntconsulting.hotel.searchservice.dto;

import java.math.BigDecimal;

public record SearchRequestDTO(String country, String city, String Address, Integer quantityPeople,
                               BigDecimal priceStart, BigDecimal priceEnd, Integer rating, String startAt, String endAt) {
}
