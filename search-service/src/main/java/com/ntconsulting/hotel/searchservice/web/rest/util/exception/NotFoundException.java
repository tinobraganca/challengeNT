package com.ntconsulting.hotel.searchservice.web.rest.util.exception;


import com.ntconsulting.hotel.searchservice.web.rest.util.error.ApiError;

public class NotFoundException extends RuntimeException{

    private ApiError apiError;

    public NotFoundException(ApiError apiError){
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
