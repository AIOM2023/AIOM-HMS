package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.response.ServiceRequestResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.ServiceRequestRepo;
import com.hospital.management.service.ServiceRequestService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRequestServiceImpl.class);

    @Autowired
    ServiceRequestRepo serviceRequestRepo;

    @Override
    public ServiceRequest save(ServiceRequest serviceRequest) {
        LOGGER.info("Creating a new country");
        Long maxId = serviceRequestRepo.getMaxId();
        serviceRequest.setServiceRequestCode("SR"+(maxId == null ? 1 : maxId+1));
        serviceRequest.setCreatedBy("System");
        serviceRequest.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        serviceRequest.setStatus(0);
        return serviceRequestRepo.save(serviceRequest);

    }

    @Override
    public ServiceRequest update(ServiceRequest serviceRequest,Long serviceRequestId) {
        if(!isServiceRequestExist(serviceRequestId)) {
            LOGGER.error("updateServiceRequest() - Given serviceRequestId is not exist");
            throw new ResourceNotFoundException(String.format("ServiceRequest not found with the given Id: %s", serviceRequestId));
        }
        serviceRequest.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        serviceRequest.setModifiedBy("System");
        return serviceRequestRepo.save(serviceRequest);

    }

    @Override
    public ServiceRequestResult serviceRequestList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all Service request");
        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<ServiceRequest> pages = serviceRequestRepo.findAllServiceRequests(search, pageable);

        return mapToServiceRequestResult(pageNo, pageSize,pages);


    }
    @Override
    public ServiceRequest findServiceRequestById(Long serviceRequestId) {
        LOGGER.info("Fetching ServiceRequest`    id");
        Optional<ServiceRequest> serviceRequest = serviceRequestRepo.findByServiceRequestIdAndStatus(serviceRequestId, 0);
        return serviceRequest.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Service Request not found with the given Id: %s", serviceRequestId)));
    }

    @Transactional
    @Override
    public String deleteServiceRequestById(Long serviceRequestId) {
        if(!isServiceRequestExist(serviceRequestId)) {
            LOGGER.error("deleteServiceRequestById() - Given serviceRequestId is not exist");
            throw new ResourceNotFoundException(String.format("ServiceRequest not found with the given Id: %s", serviceRequestId));
        }
        try{
            serviceRequestRepo.deleteServiceRequestById(serviceRequestId);
        } catch (Exception ex){
            LOGGER.error("deleteServiceRequestById() - Unable to delete ServiceRequest with the given Id: {}", serviceRequestId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete ServiceRequest with the given Id: %s", serviceRequestId), errorResponse);
        }

        return "ServiceRequest deleted successfully!";
    }

    private boolean isServiceRequestExist(Long serviceRequestId){
        return serviceRequestRepo.findByServiceRequestIdAndStatus(serviceRequestId, 0).isPresent();
    }

    private ServiceRequestResult mapToServiceRequestResult(int pageNo, int pageSize, Page<ServiceRequest> pages) {
        ServiceRequestResult serviceRequestResult = new ServiceRequestResult();
        serviceRequestResult.setMetaData(HmsCommonUtil.getMetaData((long) pages.getTotalElements(), (long) pages.getTotalPages(), pageNo, pageSize));
        serviceRequestResult.setData(pages.getContent());
        return serviceRequestResult;
    }
}
