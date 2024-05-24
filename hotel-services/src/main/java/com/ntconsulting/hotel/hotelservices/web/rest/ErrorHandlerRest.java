package com.ntconsulting.hotel.hotelservices.web.rest;


import com.ntconsulting.hotel.hotelservices.web.rest.util.error.ApiError;
import com.ntconsulting.hotel.hotelservices.web.rest.util.exception.NotFoundException;
import com.ntconsulting.hotel.hotelservices.web.rest.util.exception.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandlerRest extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                              HttpStatusCode httpStatusCode, WebRequest request){
        ApiError error = new ApiError(LocalDateTime.now().toString(), HttpStatus.INTERNAL_SERVER_ERROR,
                500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handlerNotFoundException(NotFoundException ex){
        ApiError error = new ApiError(LocalDateTime.now().toString(), ex.getApiError().httpStatus(),
                ex.getApiError().code(), ex.getApiError().error());
        return ResponseEntity.status(ex.getApiError().httpStatus()).body(error);

    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handlerUnauthorizedException(UnauthorizedException ex){
        ApiError error = new ApiError(LocalDateTime.now().toString(), ex.getApiError().httpStatus(),
                ex.getApiError().code(), ex.getApiError().error());
        return ResponseEntity.status(ex.getApiError().httpStatus()).body(error);
    }
}
