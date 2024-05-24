    package com.ntconsulting.hotel.userservice.enumaration;

public enum BookingStatusEnum {

    BOOKED("BOOKED WITH SUCCESS"),
    CANCELLED("BOOK CANCELLED"),
    PAYMENT_WAITING("WAINTING"),
    PAYMENT_DECLINED("NEGATE");

    final String message;

    BookingStatusEnum(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


