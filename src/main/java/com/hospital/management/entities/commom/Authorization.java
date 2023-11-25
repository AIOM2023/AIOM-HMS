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
    private  String designation;

    @Column(name="reference_code")
    private String referenceCode;

    @Column(name="op_conces")
    private String opConces;

    @Column(name="op_credit")
    private String opCredit;

    @Column(name="op_canc")
    private String opCanc;

    @Column(name="op_phar_conc")
    private String opPharConc;

    @Column(name="op_phar_due")
    private String opPharDue;

    @Column(name="ip_conces")
    private String ipConces;

    @Column(name="ip_credit")
    private String ipCredit;

    @Column(name="ip_canc")
    private String ipCanc;

    @Column(name="pat_bil_conv")
    private String patBilConv;

    @Column(name="fnb_conces")
    private String fnbConces;

    @Column(name="vouch_appr")
    private String vouchAppr;

    @Column(name="mod_appr_trans")
    private String modApprTrans;

    @Column(name="disch_wo_settl")
    private String dischWoSettl;

    @Column(name="fnb_due")
    private String fnbDue;

    @Column(name="pat_reg")
    private String patReg;

    @Column(name="createdDate")
    private OffsetDateTime createdDate;

    @Column(name="createdBy")
    private String createdBy;

    @Column(name="modifiedDate")
    private OffsetDateTime modifiedDate;

    @Column(name="modifiedBy")
    private String modifiedBy;

    @Column(name="del_auth")
    private Integer delAuth;

    @Column(name="InActive")
    private Integer InActive;

}
