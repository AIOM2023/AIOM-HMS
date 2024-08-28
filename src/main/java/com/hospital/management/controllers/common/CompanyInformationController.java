package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.CompanyInfo;
import com.hospital.management.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company/info")
public class CompanyInformationController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<CompanyInfo>> systemParamsList() {
        List<CompanyInfo> systemParams = companyInfoService.companyInfoList();
        return  ResponseEntity.ok(systemParams);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<CompanyInfo> saveSystemParameters(@RequestBody @Validated CompanyInfo companyInfo){
        CompanyInfo saveCompanyInfo = companyInfoService.save(companyInfo);
        return new ResponseEntity<>(saveCompanyInfo,HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<CompanyInfo> updateAuthorization(@RequestBody @Validated CompanyInfo companyInfo){
        CompanyInfo updateCompanyInfo = companyInfoService.update(companyInfo);
        return new ResponseEntity<>(updateCompanyInfo,HttpStatus.OK);
    }
}
