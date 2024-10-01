package com.hospital.management.entities.admin;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "master_referral")
public class Referral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "referral_id")
    private Long referralId;

    @Column(name= "referral_code")
    private String referralCode;

    @Column(name= "referral_source")
    private String referralSource;

    @Column(name= "referral_name")
    private String referralName;

    @Column(name= "specialization")
    private String specialization;

    @Column(name= "designation")
    private String designation;

    @Column(name= "contact_num")
    private String contactNum;

    @Column(name= "village")
    private String village;

    @Column(name= "city_name")
    private String cityName;

    @Column(name= "district_name")
    private String districtName;

    @Column(name= "state_name")
    private String stateName;

    @Column(name= "country_name")
    private String countryName;

    @Column(name= "referral_percentage")
    private String referralPercentage;

    @Column(name= "in_patient")
    private String inPatient;

    @Column(name= "investigations")
    private String investigations;

    @Column(name= "op_consultant")
    private String opConsultant;

    @Column(name= "pan_num")
    private String panNum;

    @Column(name= "bank_name")
    private String bankName;

    @Column(name= "account_num")
    private String accountNum;

    @Column(name= "address")
    private String address;

    @Column(name= "status")
    private Integer status;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;
}
