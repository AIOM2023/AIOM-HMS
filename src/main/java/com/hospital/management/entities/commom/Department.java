package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "department_id")
    private Integer departmentId;

    @Column(name="dept_code")
    private String departmentCode;

    @Column(name="dept_name")
    private String departmentName;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    private String  createdBy;

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name= "del_dept")
    private Integer delDepartment;

    @Column(name= "in_active")
    private Integer inActive;


}

