package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "employee_deatils")
public class EmployeeDeatils {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "employee_id")
    private Long employeeId;

    @Column(name= "employee_code")
    private Integer employeeCode;

    @Column(name="department")
    private String department;

    @Column(name="designation")
    private String designation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="personal_info_id")
    private PersonalInformation personalInformation;

    @OneToOne(cascade = CascadeType.ALL)
    private FinancialDetails financialDetails;


    @Column(name="employment_type")
    private String employmentType;

    @Column(name="prohibition_dt")
   // @DateTimeFormat(pattern = "dd/MM/yyyy")
    private OffsetDateTime prohibitionDt;

    @Column(name="permanent_on_date")
  //  @DateTimeFormat(pattern = "dd/MM/yyyy")
    private OffsetDateTime permanentOnDate;

    @Column(name="relieve_on_date")
 //   @DateTimeFormat(pattern = "dd/MM/yyyy")
    private OffsetDateTime relieveOnDate;

    @Column(name="resigned_dt")
  //  @DateTimeFormat(pattern = "dd/MM/yyyy")
    private OffsetDateTime resignedDt;

    @Column(name="user_role")
    private String userRole;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="profile_pic")
    private String profilePic;

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
