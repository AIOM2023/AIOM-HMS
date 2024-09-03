package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Entity
@Table(name= "personal_information")
public class PersonalInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "employee_id")
    private Long employee_id;

    @Column(name= "employee_code")
    private Integer employeeCode;

    @Column(name="tittle")
    private String tittle;

    @Column(name="last_name")
    private String lastNmae;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="father_name")
    private String fatherName;

    @Column(name="employee_type")
    private String employeeType;

    @Column(name="date_of_birth")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @Column(name="date_of_join")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfJoin;

    @Column(name="birth_place")
    private String birthPlace;

    @Column(name="blood_group")
    private String bloodGroup;

    @Column(name="gender")
    private String gender;

    @Column(name="religion")
    private String religion;

    @Column(name="civil_status")
    private String civilStatus;

    @Column(name="department")
    private String department;

    @Column(name="designation")
    private String  designation;

    @Column(name="user_role")
    private String userRole;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

   @Column(name= "status")
    private Integer status;
}
