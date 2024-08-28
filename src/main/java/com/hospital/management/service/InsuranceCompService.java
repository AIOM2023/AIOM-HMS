package com.hospital.management.service;

import com.hospital.management.entities.commom.InsuranceComp;


import java.util.List;

public interface InsuranceCompService {

    InsuranceComp saveInsuranceComp(InsuranceComp insuranceComp);
    InsuranceComp updateInsuranceComp(InsuranceComp insuranceComp, Long insComId);
    List<InsuranceComp> insuranceCompList();
    InsuranceComp findInsuranceCompByInsComId(Long insComId);
    String deleteInsuranceCompByInsComId(Long insComId);

}