package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.RegistrationFeesRepo;
import com.hospital.management.service.RegistrationFeesService;
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
public class RegistrationFeesServiceImpl implements RegistrationFeesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationFeesServiceImpl.class);
    @Autowired
    RegistrationFeesRepo registrationFeesRepo;

    @Override
    public RegistrationFees saveregistrationFees(RegistrationFees registrationFees) {
        LOGGER.info("Creating a new RegistrationFees");
        registrationFees.setCreatedBy("System");
        registrationFees.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        registrationFees.setStatus(0);
       return registrationFeesRepo.save(registrationFees);
    }

    @Override
    public RegistrationFees updateregistrationFees(RegistrationFees registrationFees,Integer regFeesId) {
        LOGGER.info("Updating an existing RegistrationFees");
        if(!isRegistrationExist(regFeesId)) {
            LOGGER.error("update() - Tariff not found with the given Id: {} ", regFeesId);
            throw new ResourceNotFoundException(String.format("RegistrationFees not found with the given Id: %s", regFeesId));
        }
        registrationFees.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        registrationFees.setModifiedBy("System");
       return registrationFeesRepo.save(registrationFees);
    }

    @Override
    public List<RegistrationFees> registrationFeesList() {
        LOGGER.info("Fetching all RegistrationFees");
        return registrationFeesRepo.findAllRegistrationFees();
    }

    @Override
    public RegistrationFees findRegistrationFeesId(Integer regFeesId) {
        LOGGER.info("Fetching RegistrationFees by id");
        Optional<RegistrationFees> registrationFees = registrationFeesRepo.findByRegFeesIdAndStatus(regFeesId, 0);
        return registrationFees.orElseThrow(() ->
                new ResourceNotFoundException(String.format("RegistrationFees not found with the given Id: %s", regFeesId)));
    }

    /*@Override
    public List<RegistrationFees> insuranceCompList() {

        return registrationFeesRepo.findAll();
    }*/
    @Transactional
    @Override
    public String deleteRegistrationFeesById(Integer regFeesId) {
        if(!isRegistrationExist(regFeesId)) {
            LOGGER.error("deleteRegistrationFeesById() - regFeeCode not found with the given Id: {} ", regFeesId);
            throw new ResourceNotFoundException(String.format("RegistrationFees not found with the given Id: %s", regFeesId));
        }
        try{
            registrationFeesRepo.deleteRegFeeById(regFeesId);
        } catch (Exception ex){
            LOGGER.error("deleteRegFeeById() - Unable to delete RegistrationFees with the given Id: {} ", regFeesId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete RegistrationFees with the given Id: %s", regFeesId), errorResponse);
        }

        return "RegistrationFees deleted successfully!";
    }

    private boolean isRegistrationExist(Integer regFeesId){
        return registrationFeesRepo.findByRegFeesIdAndStatus(regFeesId, 0).isPresent();
    }
}
