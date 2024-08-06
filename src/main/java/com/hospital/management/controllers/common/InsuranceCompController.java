package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.InsuranceComp;
import com.hospital.management.service.InsuranceCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance/comp")
public class InsuranceCompController {

    @Autowired
    InsuranceCompService insuranceCompService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<InsuranceComp>> systemParamsList() {
        List<InsuranceComp> insuranceCompList = insuranceCompService.insuranceCompList();
        return  ResponseEntity.ok(insuranceCompList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<InsuranceComp> saveInsuranceComp(@RequestBody @Validated InsuranceComp insuranceComp){
        insuranceCompService.saveInsuranceComp(insuranceComp);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<InsuranceComp> updateAuthorization(@RequestBody @Validated InsuranceComp insuranceComp,@PathVariable("insComId") Integer insComId){
        insuranceCompService.updateInsuranceComp(insuranceComp,insComId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
