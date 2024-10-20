package com.hospital.management.exceptions;


import com.hospital.management.model.GenericResponse;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.payload.ServiceResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionalHandler {

   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "BadRequest", errorResponse), HttpStatus.BAD_REQUEST);

    }*/

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<GenericResponse<ErrorResponse>> handleGenericExceptions(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "BadRequest", errorResponse), HttpStatus.BAD_REQUEST);
    }

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
