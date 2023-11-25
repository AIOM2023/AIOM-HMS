package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_lab_test_name")
public class LabTestName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "lab_test_name_id")
    private Integer labTestId;

    @Column(name= "lab_test_name_code")
    private  String labTestCode;

    @Column(name= "lab_test_name_value")
    private String labTestValue;

    @Column(name= "lab_test_name_description")
    private String labTestDescription;

}
