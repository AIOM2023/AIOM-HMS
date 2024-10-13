package com.hospital.management.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.hospital.management.entities.commom.Department;
import com.hospital.management.entities.search.DepartmentSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.repositary.DepartmentRepo;
import com.hospital.management.service.DepartmentService;
import com.hospital.management.utils.HmsCommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public DepartmentSearchResult departmentList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        log.info("Fetching all DepartmentList");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<Department> pages = departmentRepo.findAllDepartment(search, pageable);
        log.info(String.valueOf(pages.getTotalElements()));
               return mapToDepartmentSearchResult(pageNo, pageSize, pages);
    }

    private DepartmentSearchResult mapToDepartmentSearchResult(int pageNo, int pageSize, Page<Department> departments) {
        DepartmentSearchResult departmentSearchResult = new DepartmentSearchResult();
        departmentSearchResult.setMetaData(HmsCommonUtil.getMetaData(departments.getTotalElements(), (long) departments.getTotalPages(), pageNo, pageSize));
        departmentSearchResult.setData(departments.getContent());
        return departmentSearchResult;
    }

    @Override
    public List<Department> findDepartmentById(Long departmentId) {
        log.info("Fetching Department by id");
        return departmentRepo.findByDepartmentId(departmentId);
    }

    @Override
    public List<Department> departmentListAll() {
        return departmentRepo.findAllDepartmentList();
    }

    @Override
    public Department saveDepartment(Department department) {
        log.info("Creating a new Department");
        Department departmentNameExisting = departmentRepo.findByDepartmentName(department.getDepartmentName());
        if (departmentNameExisting != null) {
            throw new DuplicateEntryException("A system parameter with the name '" + departmentNameExisting.getDepartmentName() + "' already exists.");
        }
        Long maxId = departmentRepo.getMaxId();
        department.setDepartmentCode("DEP" +(maxId == null ? 1 : maxId+1));
        return departmentRepo.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        log.info("Updating an existing Department");
        Department departmentNameExisting = departmentRepo.findByDepartmentName(department.getDepartmentName());
        if(departmentNameExisting != null && !(departmentNameExisting.getDepartmentId().equals(department.getDepartmentId()))){
            if (departmentNameExisting.getDepartmentName().equals(department.getDepartmentName())){
                throw new DuplicateEntryException("Department with the name '" + departmentNameExisting.getDepartmentName() + "' already exists.");
            }
        }
        return departmentRepo.save(department);
    }

    @Override
    public Department delete(Long departmentId) {
        Department department=departmentRepo.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
        department.setStatus(1);
        departmentRepo.save(department);
        return department;
    }


}

