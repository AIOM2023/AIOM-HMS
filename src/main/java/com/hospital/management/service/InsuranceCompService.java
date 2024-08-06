package com.hospital.management.service;

import com.hospital.management.entities.commom.InsuranceComp;


import java.util.List;

public interface InsuranceCompService {

    InsuranceComp saveInsuranceComp(InsuranceComp insuranceComp);
    InsuranceComp updateInsuranceComp(InsuranceComp insuranceComp, Integer insComId);
    List<InsuranceComp> insuranceCompList();
    InsuranceComp findInsuranceCompByInsComId(Integer insComId);
    String deleteInsuranceCompByInsComId(Integer insComId);

}