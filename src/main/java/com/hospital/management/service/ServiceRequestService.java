package com.hospital.management.service;

import com.hospital.management.entities.commom.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {

    void save(ServiceRequest serviceRequest);

    void update(ServiceRequest serviceRequest);

    List<ServiceRequest> serviceRequestList();
}
