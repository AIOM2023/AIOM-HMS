package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Designation;
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
        serviceChargeService.saveServiceCharge(serviceCharge);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/update/{serviceChargeId}")
    public ResponseEntity<ServiceCharge> updateServiceCharge(@RequestBody @Validated ServiceCharge serviceCharge,@PathVariable("serviceChargeId") Integer serviceChargeId){
        serviceChargeService.updateServiceCharge(serviceCharge,serviceChargeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{serviceChargeId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<ServiceCharge> findServiceChargeById(@PathVariable("serviceChargeId") Integer serviceChargeId) {
        ServiceCharge serviceCharge = serviceChargeService.findServiceChargeById(serviceChargeId);
        return ResponseEntity.ok(serviceCharge);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{serviceChargeId}")
    public ResponseEntity<String> deleteServiceChargeById(@PathVariable("serviceChargeId") Integer serviceChargeId) {
        String successMsg = serviceChargeService.deleteServiceChargeById(serviceChargeId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }

    /*
     @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Designation>> designationList() {
        List<Designation> designation = designationService.designationList();
        return  ResponseEntity.ok(designation);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Designation> saveDesignation(@RequestBody @Validated Designation designation){
        designationService.saveDesignation(designation);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update/{designationId}")
    public ResponseEntity<Designation> updateDesignation(@RequestBody @Validated Designation designation,@PathVariable("designationId") Integer designationId){
        designationService.updateDesignation(designation,designationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
     */
}
