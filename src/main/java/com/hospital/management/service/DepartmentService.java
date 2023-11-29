package com.hospital.management.service;

import com.hospital.management.entities.commom.Department;

import java.util.List;

public interface DepartmentService {

    void save(Department department);

    void update(Department department);

    List<Department> departmentList();
}
