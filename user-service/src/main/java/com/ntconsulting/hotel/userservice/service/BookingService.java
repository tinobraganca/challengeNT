package com.ntconsulting.hotel.userservice.service;

import com.ntconsulting.hotel.userservice.dto.RequestBookingDTO;

public interface BookingService {
    void createBooking(RequestBookingDTO requestBooking);
}
