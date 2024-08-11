package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.DepartmentRepo;
import com.hospital.management.service.DepartmentService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    public Department saveDepartment(Department department) {
        LOGGER.info("Creating a new HowDid");
        department.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        department.setCreatedBy("System");
        department.setStatus(0);
       return departmentRepo.save(department);
    }

    @Override
    public Department updateDepartmentById(Department department,Integer departmentId) {
        LOGGER.info("Updating an existing Department");
        if(!isDepartmentExist(departmentId)) {
            LOGGER.error("update() - Department not found with the given Id: {} ", departmentId);
            throw new ResourceNotFoundException(String.format("Department not found with the given Id: %s", departmentId));
        }
        department.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        department.setModifiedBy("System");

         return departmentRepo.save(department);
    }

    @Override
    public List<Department> departmentList() {
        LOGGER.info("Fetching all DepartmentList");
               return departmentRepo.findAllDepartment();
    }

    @Override
    public Department findDepartmentById(Integer departmentId) {
        LOGGER.info("Fetching howDidId by id");
        Optional<Department> department = departmentRepo.findByDepartmentIdAndStatus(departmentId, 0);
        return department.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Department not found with the given Id: %s", departmentId)));
    }

    @Transactional
    @Override
    public String deleteDepartmentById(Integer departmentId) {
        if(!isDepartmentExist(departmentId)) {
            LOGGER.error("deleteDepartmentById() - Department not found with the given Id: {} ", departmentId);
            throw new ResourceNotFoundException(String.format("Department not found with the given Id: %s", departmentId));
        }
        try{
            departmentRepo.deleteDepartmentById(departmentId);
        } catch (Exception ex){
            LOGGER.error("deleteDepartmentById() - Unable to delete Department with the given Id: {} ", departmentId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Department with the given Id: %s", departmentId), errorResponse);
        }

        return "Department deleted successfully!";
    }



    private boolean isDepartmentExist(Integer departmentId){
        return departmentRepo.findByDepartmentIdAndStatus(departmentId, 0).isPresent();
    }

}

