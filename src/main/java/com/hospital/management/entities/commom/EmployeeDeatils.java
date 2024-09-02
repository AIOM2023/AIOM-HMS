package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Entity
@Table(name= "employee_deatils")
public class EmployeeDeatils {

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

    @Column(name="blood_group")
    private String bloodGroup;

    @Column(name="gender")
    private String gender;

    @Column(name="religion")
    private String religion;

    @Column(name="civil_status")
    private String civilStatus;

    @Column(name="department")
    private String department;

    @Column(name="designation")
    private String  designation;

    @Column(name="user_role")
    private String userRole;

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
