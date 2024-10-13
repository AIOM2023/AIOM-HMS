package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.CompanyInfo;
import com.hospital.management.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/company/info")
public class CompanyInformationController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @GetMapping
    public ResponseEntity<List<CompanyInfo>> companyInfoList() {
        List<CompanyInfo> companyInfoList = companyInfoService.companyInfoList();
        return  ResponseEntity.ok(companyInfoList);

    }

    @PostMapping("/save")
    public ResponseEntity<CompanyInfo> saveSystemParameters(@RequestParam("logo") MultipartFile logo,
                                                            @RequestParam("companyName") String companyName,
                                                            @RequestParam("companyAddress") String companyAddress,
                                                            @RequestParam("companyContactNo")String companyContactNo,
                                                            @RequestParam("tinNo") String tinNo) {
        CompanyInfo saveCompanyInfo = companyInfoService.save(logo,companyName,companyAddress,companyContactNo,tinNo);
        return new ResponseEntity<>(saveCompanyInfo, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<CompanyInfo> updateAuthorization(@RequestParam("logo") MultipartFile logo,
                                                           @RequestParam("companyName") String companyName,
                                                           @RequestParam("companyAddress") String companyAddress,
                                                           @RequestParam("companyContactNo")String companyContactNo,
                                                           @RequestParam("tinNo") String tinNo){
        CompanyInfo updateCompanyInfo = companyInfoService.update(logo,companyName,companyAddress,companyContactNo,tinNo);
        return new ResponseEntity<>(updateCompanyInfo,HttpStatus.OK);
    }
}
