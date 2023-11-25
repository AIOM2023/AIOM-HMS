package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_test_specimen")
public class TestSpecimen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "test_specimen_id")
    private Integer testSpecimenId;

    @Column(name= "test_specimen_code")
    private String testSpecimenCode;

    @Column(name= "test_specimen_value")
    private String testSpecimenValue;

    @Column(name= "test_specimen_description")
    private String testSpecimenDescription;
}
