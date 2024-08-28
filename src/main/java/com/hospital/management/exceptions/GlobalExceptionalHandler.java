package com.hospital.management.exceptions;


import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.payload.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleDuplicateEntryException(DuplicateEntryException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
