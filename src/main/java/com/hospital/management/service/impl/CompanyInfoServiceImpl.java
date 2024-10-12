package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.CompanyInfo;
import com.hospital.management.repositary.CompanyInfoRepo;
import com.hospital.management.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    CompanyInfoRepo companyInfoRepo;


    @Override
    public CompanyInfo save(MultipartFile logo, String companyName, String tinNo, String companyAddress, String contactNo) {
        CompanyInfo companyInfo = new CompanyInfo();
        try {
            companyInfo.setLogo(logo.getBytes());
            companyInfo.setCompanyName(companyName);
            companyInfo.setCompanyAddress(companyAddress);
            companyInfo.setCompanyContactNo(contactNo);
            companyInfo.setTinNo(tinNo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return companyInfoRepo.save(companyInfo);
    }

    @Override
    public CompanyInfo update(MultipartFile logo, String companyName, String tinNo, String companyAddress, String contactNo) {

        CompanyInfo companyInfo = new CompanyInfo();
        try {
            companyInfo.setLogo(logo.getBytes());
            companyInfo.setCompanyName(companyName);
            companyInfo.setCompanyAddress(companyAddress);
            companyInfo.setCompanyContactNo(contactNo);
            companyInfo.setTinNo(tinNo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return companyInfoRepo.save(companyInfo);

    }


    @Override
    public List<CompanyInfo> companyInfoList() {
        return companyInfoRepo.findAll();
    }
}
