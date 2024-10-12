package com.hospital.management.service;

import com.hospital.management.entities.commom.CompanyInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyInfoService {

    CompanyInfo save(MultipartFile logo,String companyName,String tinNo,String companyAddress, String contactNo);

    CompanyInfo update(MultipartFile logo,String companyName,String tinNo,String companyAddress, String contactNo);

    List<CompanyInfo> companyInfoList();
}
