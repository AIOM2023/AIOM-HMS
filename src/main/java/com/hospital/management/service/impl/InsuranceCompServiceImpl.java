package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.InsuranceComp;
import com.hospital.management.repositary.InsuranceCompRepo;
import com.hospital.management.service.InsuranceCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceCompServiceImpl implements InsuranceCompService {

    @Autowired
    InsuranceCompRepo insuranceCompRepo;

    @Override
    public void save(InsuranceComp insuranceComp) {
        insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public void update(InsuranceComp insuranceComp) {
        insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public List<InsuranceComp> insuranceCompList() {
        return insuranceCompRepo.findAll();
    }
}
