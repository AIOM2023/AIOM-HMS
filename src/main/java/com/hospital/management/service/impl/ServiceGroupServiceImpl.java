package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceGroup;
import com.hospital.management.entities.response.ServiceGroupSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.ServiceGroupRepo;
import com.hospital.management.service.ServiceGroupService;
import com.hospital.management.utils.HmsCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceGroupServiceImpl implements ServiceGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceGroupServiceImpl.class);

    @Autowired
    private ServiceGroupRepo serviceGroupRepo;

    @Override
    public ServiceGroupSearchResult getAllServiceGroups(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all Service Groups");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ServiceGroup> pages = serviceGroupRepo.findAllServiceGroups(search, pageable);

        return mapToServiceGroupSearchResult(pageNo, pageSize, pages.getContent());

    }
    private ServiceGroupSearchResult mapToServiceGroupSearchResult(int pageNo, int pageSize, List<ServiceGroup> serviceGroups) {
        ServiceGroupSearchResult serviceGroupSearchResult = new ServiceGroupSearchResult();
        Long totalPages = (long) (serviceGroups.size() % pageSize == 0 ? serviceGroups.size() / pageSize : serviceGroups.size() / pageSize+1);
        serviceGroupSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) serviceGroups.size(), totalPages, pageNo, pageSize));
        serviceGroupSearchResult.setData(serviceGroups);

        return serviceGroupSearchResult;
    }

    @Override
    public ServiceGroup findServiceGroupById(Long serviceGroupId) {
        LOGGER.info("Fetching Room Group by serviceGroupId");
        Optional<ServiceGroup> serviceGroup = serviceGroupRepo.findByServiceGroupIdAndStatus(serviceGroupId, 0);
        return serviceGroup.orElseThrow(() ->
                new ResourceNotFoundException(String.format("ServiceGroup not found with the given Id: %s", serviceGroupId)));
    }

    @Override
    public ServiceGroup saveServiceGroup(ServiceGroup serviceGroup) {
        LOGGER.info("Creating a new ServiceGroup");

       // Long maxId = serviceGroupRepo.getMaxId();
       // serviceGroup.setServiceGroupCode(maxId == null ? 1 : maxId+1);

        serviceGroup.setCreatedBy("System");
        serviceGroup.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        serviceGroup.setStatus(0);
        return serviceGroupRepo.save(serviceGroup);
    }

    @Override
    public ServiceGroup updateServiceGroup(ServiceGroup serviceGroup, Long serviceGroupId) {
        if(!isServiceGroupExist(serviceGroupId)) {
            LOGGER.error("updateServiceGroup() - Given serviceGroupId is not exist");
            throw new ResourceNotFoundException(String.format("RoomGroup not found with the given Id: %s", serviceGroupId));
        }
        serviceGroup.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        serviceGroup.setModifiedBy("System");

        return serviceGroupRepo.save(serviceGroup);
    }

    @Transactional
    @Override
    public String deleteServiceGroupById(Long serviceGroupId) {
        if(!isServiceGroupExist(serviceGroupId)) {
            LOGGER.error("deleteServiceGroupById() - Given serviceGroupId is not exist");
            throw new ResourceNotFoundException(String.format("ServiceGroup not found with the given Id: %s", serviceGroupId));
        }
        try{
            serviceGroupRepo.deleteServiceGroupById(serviceGroupId);
        } catch (Exception ex){
            LOGGER.error("deleteRoomGroupById() - Unable to delete ServiceGroup with the given Id: {}", serviceGroupId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete ServiceGroup with the given Id: %s", serviceGroupId), errorResponse);
        }

        return "ServiceGroup deleted successfully!";
    }

    private boolean isServiceGroupExist(Long serviceGroupId){
        return serviceGroupRepo.findByServiceGroupIdAndStatus(serviceGroupId, 0).isPresent();
    }
}
