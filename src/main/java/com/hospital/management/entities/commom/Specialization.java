package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "specialization_id")
    private Integer specializationId;

    @Column(name= "specialization_code")
    private String specializationCode;

    @Column(name= "specialization_value")
    private String specializationValue;

    @Column(name= "specialization_description")
    private String specializationDescription;

}
