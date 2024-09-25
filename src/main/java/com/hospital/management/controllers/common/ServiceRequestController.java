package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.response.ServiceRequestResult;
import com.hospital.management.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/request")
public class ServiceRequestController {

    @Autowired
    ServiceRequestService serviceRequestService;



    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<ServiceRequestResult> serviceRequestDataList(
            @RequestParam(name="search") String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder ) {
        ServiceRequestResult serviceRequestResult = serviceRequestService.serviceRequestList(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(serviceRequestResult);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ServiceRequest> saveServiceRequest(@RequestBody @Validated ServiceRequest serviceRequest){
        ServiceRequest saveServiceRequest = serviceRequestService.save(serviceRequest);
        return new ResponseEntity<>(saveServiceRequest,HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update/{serviceRequestId}")
    public ResponseEntity<ServiceRequest> updateServiceRequest(@RequestBody @Validated ServiceRequest serviceRequest, @PathVariable("serviceRequestId") Long serviceRequestId){
        ServiceRequest UpdateServiceRequest = serviceRequestService.update(serviceRequest,serviceRequestId);
        return new ResponseEntity<>(UpdateServiceRequest,HttpStatus.OK);
    }

    @GetMapping("/{serviceRequestId}")
    public ResponseEntity<ServiceRequest> findCountryById(@PathVariable("serviceRequestId") Long serviceRequestId) {
        ServiceRequest serviceRequest = serviceRequestService.findServiceRequestById(serviceRequestId);
        return ResponseEntity.ok(serviceRequest);
    }

    @DeleteMapping("/delete/{serviceRequestId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable("serviceRequestId") Long serviceRequestId){
        return new ResponseEntity<>(serviceRequestService.deleteServiceRequestById(serviceRequestId), HttpStatus.OK);
    }
}
