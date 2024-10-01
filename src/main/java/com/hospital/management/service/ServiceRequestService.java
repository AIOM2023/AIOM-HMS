package com.hospital.management.service;

import com.hospital.management.entities.commom.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {

    ServiceRequest save(ServiceRequest serviceRequest);

    ServiceRequest update(ServiceRequest serviceRequest);

    List<ServiceRequest> serviceRequestList();
}
