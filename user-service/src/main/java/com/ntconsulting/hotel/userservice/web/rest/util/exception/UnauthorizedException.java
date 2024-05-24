package com.ntconsulting.hotel.userservice.web.rest.util.exception;

import com.ntconsulting.hotel.userservice.web.rest.util.error.ApiError;

public class UnauthorizedException extends RuntimeException{

    private final ApiError apiError;

    public UnauthorizedException(ApiError apiError){
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
