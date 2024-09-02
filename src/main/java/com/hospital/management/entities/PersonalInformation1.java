package com.hospital.management.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "Personal_information")
public class PersonalInformation1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "EMPLOYEE_ID")
    private  Integer employeeId;

    @Column(name="TITTLE")
    private  String title;

    @Column(name="FIRST_NAME")
    private  String firstName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="ID_TYPE")
    private  String idType;

    @Column(name="ID_NUMBER")
    private  String idNumber;

    @Column(name="GENDER")
    private String gender;





}
