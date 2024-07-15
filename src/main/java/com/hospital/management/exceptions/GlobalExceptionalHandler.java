package com.hospital.management.exceptions;


import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.payload.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ServiceResponse> handlerResourecNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ServiceResponse response= ServiceResponse.builder().message(message).success(false).httpStatus(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<ServiceResponse>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HmsBusinessException.class)
    public ResponseEntity<ErrorResponse> handleHmsBusinessException(HmsBusinessException ex, WebRequest req){
        ErrorResponse errorResponse = ex.getErrorResponse();
        return new ResponseEntity<ErrorResponse>(errorResponse, errorResponse.getHttpStatus());
    }
}
