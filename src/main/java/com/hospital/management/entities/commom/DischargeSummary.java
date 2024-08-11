package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "discharge_summary")
public class DischargeSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "dischrg_id")
    private Long dischargeId;

    @Column(name= "dischrg_no")
    private String dischargeNo;

    @Column(name= "dischrg_date")
    private OffsetDateTime dischargeDate;

    @Column(name= "umr_no")
    private String umrNo;

    @Column(name= "reg_no")
    private String regNo;

    @Column(name= "admit_no")
    private String admitNo;

    @Column(name= "dept")
    private String dept;

    @Column(name= "formate")
    private String formate;

    @Column(name= "summary")
    private String summary;

    @Column(name= "status")
    private Integer status;

    @Column(name= "appr_dis")
    private Integer apprDis;

    @Column(name= "del_dis")
    private Integer delDis;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

  /*  @Column(name= "status")
    private Integer status;*/
}
