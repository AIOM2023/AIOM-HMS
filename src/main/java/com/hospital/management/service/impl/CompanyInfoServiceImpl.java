package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.CompanyInfo;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.repositary.CompanyInfoRepo;
import com.hospital.management.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    @Autowired
    CompanyInfoRepo companyInfoRepo;


    @Override
    public CompanyInfo save(MultipartFile logo, String companyName, String tinNo, String companyAddress, String contactNo) {

        CompanyInfo companyNameExisting = companyInfoRepo.findByCompanyName(companyName);
        if (companyNameExisting != null) {
            throw new DuplicateEntryException(companyName + "already exists.");
        }
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
    public CompanyInfo update(Long companyId,MultipartFile logo, String companyName, String tinNo, String companyAddress, String contactNo) {

        CompanyInfo companyNameExisting = companyInfoRepo.findByCompanyName(companyName);
        if(companyNameExisting != null && !(companyNameExisting.getCompanyId().equals(companyId))) {
            if (companyNameExisting.getCompanyName().equals(companyName)) {
                throw new DuplicateEntryException(companyName + "' already exists.");
            }
        }
        CompanyInfo companyInfo = new CompanyInfo();
        try {
            companyInfo.setCompanyId(companyId);
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

    @Override
    public Optional<CompanyInfo> companyInfoListById(Long companyId) {
        return companyInfoRepo.findById(companyId);
    }
}
