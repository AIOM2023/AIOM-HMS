package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "common_authorization")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "auth_id")
    private Integer authorizationId;

    @Column(name="auth_code")
    private String authorizationCode;

    @Column(name="auth_name")
    private String authorizationName;

    @Column(name="designation")
    private Integer designation;

    @Column(name="reference_code")
    private String referenceCode;

    @Column(name="op_conces")
    private Integer opConces;

    @Column(name="op_credit")
    private Integer opCredit;

    @Column(name="op_canc")
    private Integer opCanc;

    @Column(name="op_phar_conc")
    private Integer opPharConc;

    @Column(name="op_phar_due")
    private Integer opPharDue;

    @Column(name="ip_conces")
    private Integer ipConces;

    @Column(name="ip_credit")
    private Integer ipCredit;

    @Column(name="ip_canc")
    private Integer ipCanc;

    @Column(name="pat_bil_conv")
    private Integer patBilConv;

    @Column(name="fnb_conces")
    private Integer fnbConces;

    @Column(name="vouch_appr")
    private Integer vouchAppr;

    @Column(name="mod_appr_trans")
    private Integer modApprTrans;

    @Column(name="disch_wo_settl")
    private Integer dischWoSettl;

    @Column(name="fnb_due")
    private Integer fnbDue;

    @Column(name="pat_reg")
    private Integer patReg;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String modifiedBy;

    @Column(name="del_auth")
    private Integer delAuth;

    @Column(name="InActive")
    private Integer InActive;

}
