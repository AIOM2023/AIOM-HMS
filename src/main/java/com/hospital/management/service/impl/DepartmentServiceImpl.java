package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Authorization;
import com.hospital.management.entities.commom.Department;
import com.hospital.management.repositary.AuthorizationRepo;
import com.hospital.management.repositary.DepartmentRepo;
import com.hospital.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public void save(Department department) {
        departmentRepo.save(department);
    }

    @Override
    public void update(Department department) {
        departmentRepo.save(department);
    }

    @Override
    public List<Department> departmentList() { return departmentRepo.findAll();}

}

