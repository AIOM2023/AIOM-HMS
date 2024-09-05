package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Entity
@Table(name= "contact_Information")
public class ContactInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "employee_id")
    private Long employee_id;

    @Column(name= "employee_code")
    private Integer employeeCode;

    @Column(name="pf_no")
    private String pfNo;

    @Column(name="pay_mode")
    private String paymentMode;

    @Column(name="ledger_no")
    private String ledgerNo;

    @Column(name="probation_dt")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date probationDt;

    @Column(name="permanent_on_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date permanentOnDate;

    @Column(name="relieve_on_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date relieveOnDate;

    @Column(name="resigned_dt")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date resignedDt;

    @Column(name="pan_number")
    private String panNumber;

    @Column(name="uan_no")
    private String uanNo;

    @Column(name="aadhar_no")
    private String aadharNo;

    @Column(name="driving_lincence_no")
    private String drvingLincenceNo;

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
