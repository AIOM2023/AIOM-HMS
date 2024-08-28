package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.CompanyInfo;
import com.hospital.management.repositary.CompanyInfoRepo;
import com.hospital.management.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    CompanyInfoRepo companyInfoRepo;
    @Override
    public CompanyInfo save(CompanyInfo companyInfo) {
        return companyInfoRepo.save(companyInfo);
    }

    @Override
    public CompanyInfo update(CompanyInfo companyInfo) {

        return companyInfoRepo.save(companyInfo);

    }

    @Override
    public List<CompanyInfo> companyInfoList() {
        return companyInfoRepo.findAll();
    }
}
