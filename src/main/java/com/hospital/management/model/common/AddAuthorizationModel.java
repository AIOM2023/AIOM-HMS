package com.hospital.management.model.common;

import lombok.Data;

@Data
public class AddAuthorizationModel {

    private Integer authorizationId;

    private String designation;

    private String authorizationCode;

    private String authorizationName;

    private String authorizationFor;

}
