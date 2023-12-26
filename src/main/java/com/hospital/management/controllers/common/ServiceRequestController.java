package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ServiceCategory;
import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/request")
public class ServiceRequestController {

    @Autowired
    ServiceRequestService serviceRequestService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ServiceRequest>> serviceRequestDataList() {
        List<ServiceRequest> serviceRequestList = serviceRequestService.serviceRequestList();
        return  ResponseEntity.ok(serviceRequestList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ServiceRequest> saveServiceRequest(@RequestBody @Validated ServiceRequest serviceRequest){
        serviceRequestService.save(serviceRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ServiceRequest> updateServiceRequest(@RequestBody @Validated ServiceRequest serviceRequest){
        serviceRequestService.save(serviceRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
