package com.hospital.management.service;

import com.hospital.management.entities.commom.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department updateDepartmentById(Department department,Integer departmentId);

    List<Department> departmentList();

    Department findDepartmentById(Integer departmentId);
    String deleteDepartmentById(Integer departmentId);

}
