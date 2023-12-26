package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "billing_head")
public class BillingHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "billing_head_id")
    private Integer billingHeadId;

    @Column(name= "billing_head_code")
    private String billingHeadCode;

    @Column(name= "billingHeadName")
    private String billing_head_name;

    @Column(name= "service_type")
    private String serviceType;

    @Column(name= "billing_head_desc")
    private String billingHeadDesc;

    @Column(name= "priority")
    private Integer priority;

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

    @Column(name= "del_billing_head")
    private Integer delBillingHead;

    @Column(name= "in_active")
    private Integer inActive;
}
