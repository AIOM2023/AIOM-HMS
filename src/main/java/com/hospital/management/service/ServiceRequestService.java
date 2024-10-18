package com.hospital.management.service;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.search.ServiceRequestSearchList;

import java.util.List;

public interface ServiceRequestService {

    ServiceRequestSearchList getServiceRequestSearchList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    ServiceRequest save(ServiceRequest serviceRequest);

    ServiceRequest update(ServiceRequest serviceRequest);

    ServiceRequest delete(Long serviceRequestId);

    List<ServiceRequest> serviceRequestList();

    List<ServiceRequest> serviceRequestById(Long serviceRequestId);
}
