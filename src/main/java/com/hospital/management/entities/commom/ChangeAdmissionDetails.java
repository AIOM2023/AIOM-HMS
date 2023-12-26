package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "change_admission_details")
public class ChangeAdmissionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "ca_id")
    private Integer caId;

    @Column(name= "reg_no")
    private String regNo;

    @Column(name= "umr_no")
    private String umrNo;

    @Column(name= "patnt_name")
    private String patntName;

    @Column(name= "admit_no")
    private String admitNo;

    @Column(name= "refsrc")
    private String refSrc;

    @Column(name= "conslt_code")
    private String consltCode;

    @Column(name= "conslt_name")
    private String consltName;

    @Column(name= "reff_by")
    private String reffBy;

    @Column(name= "auth_code")
    private String authCode;

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

    @Column(name= "in_active")
    private Integer inActive;
}
