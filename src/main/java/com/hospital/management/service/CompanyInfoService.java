package com.hospital.management.service;

import com.hospital.management.entities.commom.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {

    void save(CompanyInfo companyInfo);

    void update(CompanyInfo companyInfo);

    List<CompanyInfo> companyInfoList();
}
