package com.hospital.management.service;

import com.hospital.management.entities.commom.CompanyInfo;

import java.util.List;

public interface CompanyInfoService {

    CompanyInfo save(CompanyInfo companyInfo);

    CompanyInfo update(CompanyInfo companyInfo);

    List<CompanyInfo> companyInfoList();
}
