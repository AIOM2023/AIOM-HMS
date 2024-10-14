package com.hospital.management.service;

import com.hospital.management.entities.commom.CompanyInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CompanyInfoService {

    CompanyInfo save(MultipartFile logo,String companyName,String tinNo,String companyAddress, String contactNo);

    CompanyInfo update(Long companyId,MultipartFile logo,String companyName,String tinNo,String companyAddress, String contactNo);

    List<CompanyInfo> companyInfoList();

    Optional<CompanyInfo> companyInfoListById(Long companyId);
}
