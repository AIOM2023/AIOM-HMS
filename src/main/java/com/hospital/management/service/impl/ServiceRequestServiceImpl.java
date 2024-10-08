package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.entities.search.ServiceRequestSearchList;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.repositary.ServiceRequestRepo;
import com.hospital.management.service.ServiceRequestService;
import com.hospital.management.utils.HmsCommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    ServiceRequestRepo serviceRequestRepo;

    @Override
    public ServiceRequestSearchList getServiceRequestSearchList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        log.info("Fetching all System Parameters");

        int actualPage = pageNo - 1; // Pages in Spring Data start from 0
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(actualPage, pageSize,sort);
        Page<ServiceRequest> serviceRequestPage= serviceRequestRepo.findAllServiceRequests(search,pageable);

        return mapToServiceRequestSearchResult(pageNo,pageSize,serviceRequestPage);
    }

    @Override
    public ServiceRequest save(ServiceRequest serviceRequest) {
        log.info("Creating New Service Request");
        ServiceRequest serviceRequestExisting = serviceRequestRepo.findByServiceRequestName(serviceRequest.getServiceRequestName());
        if (serviceRequestExisting != null) {
            throw new DuplicateEntryException("A system parameter with the name '" + serviceRequestExisting.getServiceRequestName() + "' already exists.");
        }

        Long maxId = serviceRequestRepo.getMaxId();
        serviceRequest.setServiceRequestCode("SerReq" +(maxId == null ? 1 : maxId+1));
       return serviceRequestRepo.save(serviceRequest);
    }

    @Override
    public ServiceRequest update(ServiceRequest serviceRequest) {
        log.info("Info");
        ServiceRequest serviceRequestExisting = serviceRequestRepo.findByServiceRequestName(serviceRequest.getServiceRequestName());
        if(serviceRequestExisting != null && !(serviceRequestExisting.getServiceRequestId().equals(serviceRequest.getServiceRequestId()))){
            if (serviceRequestExisting.getServiceRequestName().equals(serviceRequest.getServiceRequestName())){
                throw new DuplicateEntryException("A system parameter with the name '" + serviceRequestExisting.getServiceRequestName() + "' already exists.");
            }
        }
        return serviceRequestRepo.save(serviceRequest);
    }

    @Override
    public ServiceRequest delete(Long serviceRequestId) {
        ServiceRequest serviceRequest=serviceRequestRepo.findById(serviceRequestId)
                .orElseThrow(() -> new RuntimeException("Id Not Found"));
        serviceRequest.setStatus(1);
        serviceRequestRepo.save(serviceRequest);
        return serviceRequest;
    }


    @Override
    public List<ServiceRequest> serviceRequestList() {
        return serviceRequestRepo.findAll();
    }

    @Override
    public List<ServiceRequest> serviceRequestById(Long serviceRequestId) {
        return serviceRequestRepo.findServiceRequestById(serviceRequestId);
    }

    private ServiceRequestSearchList mapToServiceRequestSearchResult(int pageNo, int pageSize, Page<ServiceRequest> pages) {
        ServiceRequestSearchList serviceRequestSearchList = new ServiceRequestSearchList();
        serviceRequestSearchList.setMetaData(HmsCommonUtil.getMetaData((long) pages.getTotalElements(), (long) pages.getTotalPages(), pageNo, pageSize));
        serviceRequestSearchList.setData(pages.getContent());
        return serviceRequestSearchList;
    }
}
