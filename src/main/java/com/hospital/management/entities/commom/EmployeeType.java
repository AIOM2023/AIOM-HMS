package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "common_employee_type")
public class EmployeeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "employee_type_id")
    private Integer employeeTypeId;

    @Column(name= "employee_type_code")
    private String employeeTypeCode;

    @Column(name= "employee_type_value")
    private String employeeTypeValue;

    @Column(name= "employee_type_description")
    private String employeeTypeDescription;

}
