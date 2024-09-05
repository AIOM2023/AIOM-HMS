package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "personal_information")
public class PersonalInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "personal_info_id")
    private Long personalInfoId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastNmae;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="id_type")
    private String idType;

    @Column(name="id_number")
    private String idNumber;

    @Column(name="date_of_birth")
   // @DateTimeFormat(pattern = "dd/MM/yyyy")
    private OffsetDateTime dateOfBirth;


    @Column(name="blood_group")
    private String bloodGroup;

    @Column(name="gender")
    private String gender;

    @Column(name="religion")
    private String religion;

    @Column(name="martial_status")
    private String martialStatus;

    @Column(name="email_id")
    private String emailId;

    @Column(name="country")
    private String  country;

    @Column(name="state")
    private String state;

    @Column(name="city")
    private String city;

    @Column(name="create_dDate")
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
