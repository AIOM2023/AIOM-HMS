package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_patient_title")
public class PatientTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "patient_title_id")
    private Integer patientTitleId;

    @Column(name= "patient_title_code")
    private String patientCode;

    @Column(name= "patient_title_value")
    private String patientValue;

    @Column(name= "patient_title_description")
    private String patientDescription;

}
