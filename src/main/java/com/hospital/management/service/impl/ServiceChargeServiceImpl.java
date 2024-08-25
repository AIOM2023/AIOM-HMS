package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceCharge;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.ServiceChargeRepo;
import com.hospital.management.service.ServiceChargeService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceChargeServiceImpl implements ServiceChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceChargeServiceImpl.class);
    @Autowired
    ServiceChargeRepo serviceChargeRepo;

    @Override
    public ServiceCharge saveServiceCharge(ServiceCharge serviceCharge) {
        LOGGER.info("Creating a new ServiceCharge");
        serviceCharge.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        serviceCharge.setCreatedBy("System");
        serviceCharge.setStatus(0);
        return serviceChargeRepo.save(serviceCharge);
    }

    @Override
        public ServiceCharge updateServiceCharge(ServiceCharge serviceCharge,Integer serviceChargeId) {
        LOGGER.info("Updating an existing ServiceCharge");
        if(!isServiceChargeExist(serviceChargeId)) {
            LOGGER.error("update() - ServiceCharge not found with the given Id: {} ", serviceChargeId);
            throw new ResourceNotFoundException(String.format("ServiceCharge not found with the given Id: %s", serviceChargeId));
        }
        serviceCharge.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        serviceCharge.setModifiedBy("System");


        return serviceChargeRepo.save(serviceCharge);
    }

    @Override
    public List<ServiceCharge> serviceChargeList() {
        LOGGER.info("Fetching all ServiceCharge");
        return serviceChargeRepo.findAllServiceCharge();
    }
    @Override
    public ServiceCharge findServiceChargeById(Integer serviceChargeId) {
        LOGGER.info("Fetching ServiceCharge by id");
        Optional<ServiceCharge> serviceCharge = serviceChargeRepo.findByServiceChargeIdAndStatus(serviceChargeId, 0);
        return serviceCharge.orElseThrow(() ->
                new ResourceNotFoundException(String.format("ServiceCharge not found with the given Id: %s", serviceChargeId)));
    }

    @Transactional
    @Override
    public String deleteServiceChargeById(Integer serviceChargeId) {
        if(!isServiceChargeExist(serviceChargeId)) {
            LOGGER.error("deleteServiceChargeById() - ServiceCharge not found with the given Id: {} ", serviceChargeId);
            throw new ResourceNotFoundException(String.format("ServiceCharge not found with the given Id: %s", serviceChargeId));
        }
        try{
            serviceChargeRepo.deleteServiceChargeById(serviceChargeId);
        } catch (Exception ex){
            LOGGER.error("deleteServiceChargeById() - Unable to delete ServiceCharge with the given Id: {} ", serviceChargeId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete ServiceCharge with the given Id: %s", serviceChargeId), errorResponse);
        }

        return "ServiceCharge deleted successfully!";
    }


    private boolean isServiceChargeExist(Integer serviceChargeId) {
        return serviceChargeRepo.findByServiceChargeIdAndStatus(serviceChargeId, 0).isPresent();
    }
}
