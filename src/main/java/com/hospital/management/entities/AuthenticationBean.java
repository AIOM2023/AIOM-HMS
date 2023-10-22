package com.hospital.management.entities;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationBean {

    private String username;
    private String password;

}
