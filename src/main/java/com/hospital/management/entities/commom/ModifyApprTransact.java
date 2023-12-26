package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "modify_appr_transact")
public class ModifyApprTransact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "modify_id")
    private Integer modifyId;

    @Column(name= "modify_code")
    private String modifyCode;

    @Column(name= "modify_date")
    private OffsetDateTime modifyDate;

    @Column(name= "transact_type")
    private String transactType;

    @Column(name= "transact_from")
    private OffsetDateTime transactFrom;

    @Column(name= "transact_to")
    private OffsetDateTime transactTo;

    @Column(name= "discount_no")
    private String discountNo;

    @Column(name= "modify_type")
    private String modifyType;

    @Column(name= "umr_no")
    private String umrNo;

    @Column(name= "patient_name")
    private String patientName;

    @Column(name= "authorized_by")
    private Integer authorizedBy;

    @Column(name= "remarks")
    private String remarks;

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

    @Column(name= "del_modify")
    private Integer delModify;

    @Column(name= "in_active")
    private Integer inActive;
}
