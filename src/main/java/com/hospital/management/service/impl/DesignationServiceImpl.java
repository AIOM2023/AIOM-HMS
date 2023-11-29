package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.repositary.DesignationRepo;
import com.hospital.management.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignationServiceImpl implements DesignationService {

    @Autowired
    DesignationRepo designationRepo;

    @Override
    public void save(Designation designation) {
        designationRepo.save(designation);
    }

    @Override
    public void update(Designation designation) {
        designationRepo.save(designation);
    }

    @Override
    public List<Designation> designationList() {
        return designationRepo.findAll();
    }
}
