package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.CompanyInfo;
import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.CompanyInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/company/info")
public class CompanyInformationController {

    @Autowired
    private CompanyInfoService companyInfoService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<CompanyInfo>>> companyInfoList() {

        List<CompanyInfo> companyInfoList = new ArrayList<>();
        try {
            companyInfoList = companyInfoService.companyInfoList();
            if (!companyInfoList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Company Info List", companyInfoList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Company Info List", companyInfoList), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Company Info found", companyInfoList), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", companyInfoList), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id")
    public ResponseEntity<GenericResponse<Optional<CompanyInfo>>> findCompanyInfoById(@RequestParam Long companyId) {
        log.info("Discharge List By Id");
        Optional<CompanyInfo> companyInfoListById = companyInfoService.companyInfoListById(companyId);
        if (companyInfoListById.isPresent()) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Company Info List By Id", companyInfoListById), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Company Info List with this Id", companyInfoListById), HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<CompanyInfo>> saveSystemParameters(@RequestParam("logo") MultipartFile logo,
                                                            @RequestParam("companyName") String companyName,
                                                            @RequestParam("companyAddress") String companyAddress,
                                                            @RequestParam("companyContactNo")String companyContactNo,
                                                            @RequestParam("tinNo") String tinNo) {


        CompanyInfo saveCompanyInfo = new CompanyInfo();
        try {
            saveCompanyInfo = companyInfoService.save(logo,companyName,companyAddress,companyContactNo,tinNo);
            if (saveCompanyInfo != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "Company Info Created Successfully", saveCompanyInfo), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Company Info Not Created", new CompanyInfo()), HttpStatus.OK);
            }

        }catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Company Name Already Exists", saveCompanyInfo), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveCompanyInfo), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<CompanyInfo> updateAuthorization(@RequestParam("logo") MultipartFile logo,
                                                           @RequestParam("companyName") String companyName,
                                                           @RequestParam("companyAddress") String companyAddress,
                                                           @RequestParam("companyContactNo")String companyContactNo,
                                                           @RequestParam("tinNo") String tinNo,
                                                           @RequestParam("companyId") Long companyId){
        CompanyInfo updateCompanyInfo = companyInfoService.update(companyId,logo,companyName,companyAddress,companyContactNo,tinNo);
        return new ResponseEntity<>(updateCompanyInfo,HttpStatus.OK);
    }
}
