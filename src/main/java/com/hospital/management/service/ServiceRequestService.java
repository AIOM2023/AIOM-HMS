package com.hospital.management.service;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.response.ServiceRequestResult;

public interface ServiceRequestService {

    ServiceRequest save(ServiceRequest serviceRequest);

    ServiceRequest update(ServiceRequest serviceRequest,Long serviceRequestId);

    ServiceRequestResult serviceRequestList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    ServiceRequest findServiceRequestById(Long serviceRequestId);

    String deleteServiceRequestById(Long serviceRequestId);

}
