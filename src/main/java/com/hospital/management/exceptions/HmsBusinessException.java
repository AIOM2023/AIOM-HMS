package com.hospital.management.exceptions;

import com.hospital.management.payload.ErrorResponse;
import lombok.Getter;

@Getter
public class HmsBusinessException extends RuntimeException {

    private String message;
    private ErrorResponse errorResponse;

    public HmsBusinessException(){
        super();
    }

    public HmsBusinessException(String message, ErrorResponse errorResponse){
        this.message=message;
        this.errorResponse = errorResponse;
    }
}
