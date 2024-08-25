package com.hospital.management.model;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {
    private int status;
    private String message;
    private T data;

}
