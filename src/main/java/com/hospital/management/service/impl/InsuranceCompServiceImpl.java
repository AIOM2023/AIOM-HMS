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
    public InsuranceComp saveInsuranceComp(InsuranceComp insuranceComp) {
       return insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public InsuranceComp updateInsuranceComp(InsuranceComp insuranceComp,Integer insComId) {
        return insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public List<InsuranceComp> insuranceCompList() {
        return insuranceCompRepo.findAll();
    }

    @Override
    public InsuranceComp findInsuranceCompByInsComId(Integer insComId) {
        return null;
    }

    @Override
    public String deleteInsuranceCompByInsComId(Integer insComId) {
        return "";
    }
}
