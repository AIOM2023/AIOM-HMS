package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_method")
public class Method {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "method_id")
    private Integer methodId;

    @Column(name= "method_code")
    private String methodCode;

    @Column(name= "method_value")
    private String methodValue;

    @Column(name= "method_description")
    private String methodDescription;

}
