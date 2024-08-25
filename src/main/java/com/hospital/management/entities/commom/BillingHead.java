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
    private Long billingHeadId;

    @Column(name= "billing_head_code")
    private String billingHeadCode;

    @Column(name= "billing_head_name")
    private String billingHeadName;

    @Column(name= "service_type")
    private String serviceType;

    @Column(name= "billing_head_desc")
    private String billingHeadDesc;

    private Integer status;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

}
