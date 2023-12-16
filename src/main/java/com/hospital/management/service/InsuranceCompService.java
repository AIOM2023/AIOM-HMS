package com.hospital.management.service;

import com.hospital.management.entities.commom.InsuranceComp;

import java.util.List;

public interface InsuranceCompService {

    void save(InsuranceComp InsuranceComp);
    void update(InsuranceComp insuranceComp);
    List<InsuranceComp> insuranceCompList();
}