package com.ntconsulting.hotel.userservice.enumaration;

public enum PaymentStatusEnum {
    PAYMENT_APPROVED("APPROVED"),
    PAYMENT_WAITING("WAINTING"),
    PAYMENT_DECLINED("NEGATE");

    final String message;

    PaymentStatusEnum(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
