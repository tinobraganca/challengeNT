package com.ntconsulting.hotel.searchservice.web.rest.util.exception;


import com.ntconsulting.hotel.searchservice.web.rest.util.error.ApiError;

public class UnauthorizedException extends RuntimeException{

    private final ApiError apiError;

    public UnauthorizedException(ApiError apiError){
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
