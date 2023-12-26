package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "service_charge")
public class ServiceCharge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "service_charge_id")
    private Integer serviceChargeId;

    @Column(name= "service_charge_code")
    private String serviceChargeCode;

    @Column(name= "serviceType")
    private String service_type;

    @Column(name= "tariff")
    private String tariff;

    @Column(name= "service_grp")
    private String serviceGrp;

    @Column(name= "service_grp_name")
    private String serviceGrpName;

    @Column(name= "billing_head")
    private String billingHead;

    @Column(name= "service_name")
    private String serviceName;

    @Column(name= "service_code")
    private String serviceCode;

    @Column(name= "charge")
    private Integer charge;

    @Column(name= "applicable_for")
    private String applicableFor;

    @Column(name= "instructions")
    private String instructions;

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

    @Column(name= "del_service_charge")
    private Integer delServiceCharge;

    @Column(name= "in_active")
    private Integer inActive;

}
