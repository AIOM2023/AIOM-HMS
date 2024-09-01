package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "conschrg_ward_charges")
public class ConsultantWardCharges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "cw_id")
    private Long consChargeWardId;

    @Column(name="conschrg_code")
    String consChargeCode;

    @Column(name="conslt_name")
    String consultantName;

    @Column(name="wardgrp_name")
    String wardGrpName;

    @Column(name="normal_chrg")
    int normalCharge;

    @Column(name="emerg_chrg")
    int emergencyCharge;

    @Column(name="revisit_chrg")
    int revisitCharge;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "InActive")
    int inactive;

    @Column(name= "del_cwchrg")
    short del_conWardCharge;

    @Column(name= "status")
    private Integer status;
}
