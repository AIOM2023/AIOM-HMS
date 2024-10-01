package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.repositary.ServiceRequestRepo;
import com.hospital.management.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    ServiceRequestRepo serviceRequestRepo;

    @Override
    public ServiceRequest save(ServiceRequest serviceRequest) {
       return serviceRequestRepo.save(serviceRequest);
    }

    @Override
    public ServiceRequest update(ServiceRequest serviceRequest) {
        return serviceRequestRepo.save(serviceRequest);
    }

    @Override
    public List<ServiceRequest> serviceRequestList() {
        return serviceRequestRepo.findAll();
    }
}
