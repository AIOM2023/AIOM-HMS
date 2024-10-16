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
@RequestMapping("/appointment/time")
public class AppointmentTimeController {

    @Autowired
    InsuranceCompService insuranceCompService;

    @GetMapping
    public ResponseEntity<List<InsuranceComp>> systemParamsList() {
        List<InsuranceComp> insuranceCompList = insuranceCompService.insuranceCompList();
        return  ResponseEntity.ok(insuranceCompList);

    }

    @PostMapping("/save")
    public ResponseEntity<InsuranceComp> saveInsuranceComp(@RequestBody @Validated InsuranceComp insuranceComp){
        insuranceCompService.saveInsuranceComp(insuranceComp);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<InsuranceComp> updateAuthorization(@RequestBody @Validated InsuranceComp insuranceComp,@PathVariable("insComId") Long insComId){
        insuranceCompService.updateInsuranceComp(insuranceComp,insComId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
