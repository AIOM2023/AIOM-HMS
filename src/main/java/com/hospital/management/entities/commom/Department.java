package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "department_id")
    private Long departmentId;

    @Column(name="dept_code")
    private String departmentCode;

    @Column(name="dept_name",unique = true)
    private String departmentName;

    @Column(name="created_date")
    private OffsetDateTime createdDate;

    @Column(name="created_by")
    @Builder.Default
    private String  createdBy = "System";

    @Column(name="modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name="modified_by")
    private String  modifiedBy;

    @Column(name= "status")
    @Builder.Default
    private Integer status = 0;


}

