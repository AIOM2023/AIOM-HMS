package com.hospital.management.service;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.entities.search.DepartmentSearchResult;

import java.util.List;

public interface DepartmentService {

    DepartmentSearchResult departmentList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<Department> findDepartmentById(Long departmentId);

    List<Department> departmentListAll();

    Department saveDepartment(Department department);

    Department updateDepartment(Department department);

    Department delete(Long departmentId);

}
