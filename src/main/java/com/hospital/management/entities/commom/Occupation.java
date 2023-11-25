package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_occupation")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "occupation_id")
    private Integer occupationId;

    @Column(name= "occupation_code")
    private String occupationCode;

    @Column(name= "occupation_value")
    private String occupationValue;

    @Column(name= "occupation_description")
    private String occupationDescription;

}
