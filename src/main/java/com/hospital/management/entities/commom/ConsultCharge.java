package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "consult_charge")
public class ConsultCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "consult_id")
    private Integer consultId;

    @Column(name= "consult_code")
    private String consultCode;

    @Column(name= "tariff")
    private String tariff;

    @Column(name= "doctor_code")
    private String doctorCode;

    @Column(name= "doctor_name")
    private String doctorName;

    @Column(name= "billing_head_code")
    private String billingHeadCode;

    @Column(name= "consult_normal_fee")
    private Integer consultNormalFee;

    @Column(name= "consult_emerg_fee")
    private Integer consultEmergFee;

    @Column(name= "consult_revisit_fee")
    private Integer consultRevisitFee;
    
    @Column(name= "registration_fee")
    private Integer registrationFee;

    @Column(name= "no_of_days")
    private Integer noOfDays;

    @Column(name= "no_of_visits")
    private Integer noOfVisits;

    @Column(name= "discount_op")
    private Integer discountOp;

    @Column(name= "discount_ip")
    private Integer discountIp;
    
    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "created_id")
    private String createdId;

    @Column(name= "modified_id")
    private String modifiedId;

    @Column(name= "del_consult_charge")
    private Integer delConsultCharge;
    
    @Column(name= "in_active")
    private Integer inActive;

    @Column(name= "status")
    private Integer status;
}
