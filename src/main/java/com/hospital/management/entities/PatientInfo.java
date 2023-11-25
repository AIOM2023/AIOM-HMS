package com.hospital.management.entities;

import lombok.Data;

import java.util.Date;

@Data
public class PatientInfo {

    private  String patientId;

    private  String title;

    private String firstName;

    private  String lastName;

    private String idNo;

    private  String mobileNo;

    private Date dob;

    private String gender;

    private String appointmentId;

    private String specialist;

    private  String consultantDoctor;

    private Date appointmentDt;

    private String appointmentType;

    private String appointmentPurpose;

    private String location;

    private  String branch;

    private String remarks;

}