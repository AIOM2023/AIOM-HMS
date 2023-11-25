package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_hospital_info")
public class HospitalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "hospital_info_id")
    private Integer hospitalInfoId;

    @Column(name= "hospital_info_name")
    private String hospitalName;

    @Column(name= "hospital_info_address")
    private String hospitalAddress;

    @Column(name= "hospital_info_contact_no")
    private String contactNo;

    @Column(name= "hospital_info_tin_no")
    private String tinNo;

    @Column(name= "hospital_logo")
    private byte[] hospitalLogo;

}
