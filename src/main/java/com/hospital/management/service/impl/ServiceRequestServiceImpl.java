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
    public void save(ServiceRequest serviceRequest) {
        serviceRequestRepo.save(serviceRequest);
    }

    @Override
    public void update(ServiceRequest serviceRequest) {
        serviceRequestRepo.save(serviceRequest);
    }

    @Override
    public List<ServiceRequest> serviceRequestList() {
        return serviceRequestRepo.findAll();
    }
}
