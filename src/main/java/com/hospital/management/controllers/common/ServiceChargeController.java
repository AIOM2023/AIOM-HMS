package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ServiceCharge;
import com.hospital.management.service.ServiceChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/charge")
public class ServiceChargeController {

    @Autowired
    ServiceChargeService serviceChargeService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ServiceCharge>> serviceChargeDataList() {
        List<ServiceCharge> serviceChargeList = serviceChargeService.serviceChargeList();
        return  ResponseEntity.ok(serviceChargeList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ServiceCharge> saveServiceCharge(@RequestBody @Validated ServiceCharge serviceCharge){
        serviceChargeService.save(serviceCharge);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ServiceCharge> updateServiceCharge(@RequestBody @Validated ServiceCharge serviceCharge){
        serviceChargeService.save(serviceCharge);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
