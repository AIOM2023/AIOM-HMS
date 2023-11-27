package com.hospital.management.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse{

    private String message;

   private Boolean success;

    private HttpStatus httpStatus;

}
