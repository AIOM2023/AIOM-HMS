package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_lab_sample")
public class LabSample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "lab_sample_id")
    private Integer labSampleId;

    @Column(name= "lab_sample_code")
    private String labSampleCode;

    @Column(name= "lab_sample_value")
    private String labSampleValue;

    @Column(name= "lab_sample_description")
    private String labSampleDescription;
}
