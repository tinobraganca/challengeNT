package com.ntconsulting.hotel.searchservice.web.rest.util.error;

import org.springframework.http.HttpStatus;

public record ApiError(String timestamp, HttpStatus httpStatus, int code, String error) {
}
