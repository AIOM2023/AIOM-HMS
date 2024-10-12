package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.search.ServiceRequestSearchList;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service/request")
public class ServiceRequestController {

    @Autowired
    ServiceRequestService serviceRequestService;

    @GetMapping
    public ResponseEntity<GenericResponse<ServiceRequestSearchList>> serviceRequestDataList(@RequestParam(name="search", required = false) String search,
                                                                                            @RequestParam(defaultValue = "0",required = false) int pageNo,
                                                                                            @RequestParam(defaultValue = "50",required = false) int pageSize,
                                                                                            @RequestParam(name="sortBy",required = false) String sortBy,@RequestParam(defaultValue = "DESC",required = false) String sortOrder) {
        try {
            ServiceRequestSearchList serviceRequestSearchList = serviceRequestService.getServiceRequestSearchList(search, pageNo, pageSize, sortBy, sortOrder);

            if (!serviceRequestSearchList.getData().isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Service Request Records found", serviceRequestSearchList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "System Parameters Records not found", serviceRequestSearchList), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ServiceRequestSearchList serviceRequestSearchList = new ServiceRequestSearchList();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", serviceRequestSearchList), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<ServiceRequest>>> getAllSystemRequestList(){
        try {
            List<ServiceRequest> serviceRequestList = serviceRequestService.serviceRequestList();

            if (!serviceRequestList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Service Request Records found", serviceRequestList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "System Parameters Records not found", serviceRequestList), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<ServiceRequest> serviceRequest = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", serviceRequest), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<GenericResponse<List<ServiceRequest>>> getAllSystemRequestList(@RequestParam Long serviceRequestId){
        try {
            List<ServiceRequest> serviceRequestById = serviceRequestService.serviceRequestById(serviceRequestId);

            if (!serviceRequestById.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Service Request Records found", serviceRequestById), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "System Parameters Records not found", serviceRequestById), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<ServiceRequest> serviceRequest = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", serviceRequest), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<ServiceRequest>> saveServiceRequest(@RequestBody @Validated ServiceRequest serviceRequest){
        ServiceRequest saveServiceRequest = new ServiceRequest();

        try {
             saveServiceRequest = serviceRequestService.save(serviceRequest);
            if (saveServiceRequest != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "Service Request Created Successfully", saveServiceRequest), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Service Request Not Created", saveServiceRequest), HttpStatus.OK);
            }
        }catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "System Parameter Name Already Exists", serviceRequest), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveServiceRequest), HttpStatus.OK);
        }

    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<ServiceRequest>> updateServiceRequest(@RequestBody @Validated ServiceRequest serviceRequest){
        ServiceRequest updateServiceRequest= new ServiceRequest();

        try {
            updateServiceRequest = serviceRequestService.update(serviceRequest);
            if (updateServiceRequest != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Service Request Updated Successfully", updateServiceRequest), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Service Request Not Updated", updateServiceRequest), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Service Request Name Already Exists", serviceRequest), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXCEPTION:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateServiceRequest), HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<GenericResponse<ServiceRequest>> deleteSystemParameters(@RequestParam Long serviceRequestId){
        ServiceRequest deleteServiceRequest;
        try{
            deleteServiceRequest= serviceRequestService.delete(serviceRequestId);
            if (deleteServiceRequest != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Service Request Deleted Successfully", deleteServiceRequest), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Technical Error", new ServiceRequest()), HttpStatus.OK);
            }
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(), true, "Service Request With that Id Doesn't Exists", new ServiceRequest()), HttpStatus.OK);
        }
    }

}
