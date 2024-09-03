package com.hospital.management.service;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.entities.response.DepartmentSearchResult;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Department updateDepartmentById(Department department,Integer departmentId);

 DepartmentSearchResult departmentList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    Department findDepartmentById(Integer departmentId);
    String deleteDepartmentById(Integer departmentId);

}
