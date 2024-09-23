package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "dischrg_fmt")
public class DischargeFormat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "disc_fmt_id")
    private Long discFmtId;

    @Column(name= "dept_id")
    private Integer deptId;

    @Column(name="dis_fmt_code")
    private String disfmtCode;

    @Column(name="dis_fmt_name")
    private String disFmtName;

    @Column(name="dis_fmt_descr")
    private String disFmtDescr;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

   @Column(name= "status")
    private Integer status;
}
