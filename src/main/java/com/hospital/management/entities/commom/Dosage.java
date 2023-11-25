package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_dosage")
public class Dosage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "dosage_id")
    private Integer dosageId;

    @Column(name= "dosage_code")
    private String dosageCode;

    @Column(name= "dosage_value")
    private String dosageValue;

    @Column(name= "dosage_description")
    private String dosageDescription;

}
