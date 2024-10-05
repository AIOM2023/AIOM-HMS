package com.hospital.management.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private int httpStatus;
    private boolean success;
    private String message;
    private T data;

}
