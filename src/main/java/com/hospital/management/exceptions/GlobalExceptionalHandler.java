package com.hospital.management.exceptions;


import com.hospital.management.payload.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ServiceResponse> handlerResourecNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ServiceResponse response= ServiceResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<ServiceResponse>(response,HttpStatus.NOT_FOUND);
    }
}
