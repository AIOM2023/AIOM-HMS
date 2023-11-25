package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_add_authorization")
public class AddAuthorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "add_authorization_id")
    private Integer authorizationId;

    @Column(name="add_authorization_designation")
    private String designation;

    @Column(name="add_authorization_code")
    private String authorizationCode;

    @Column(name="add_authorization_name")
    private  String authorizationName;

    @Column(name="add_authorization_for")
    private String authorizationFor;

}
