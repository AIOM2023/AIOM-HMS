package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.repositary.DesignationRepo;
import com.hospital.management.service.DesignationService;
import com.hospital.management.utils.HmsCommonUtil;
import com.hospital.management.payload.ErrorResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignationServiceImpl implements DesignationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesignationServiceImpl.class);
    @Autowired
    DesignationRepo designationRepo;

    @Override
    public Designation saveDesignation(Designation designation) {
        LOGGER.info("Creating a new HowDid");
        designation.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        designation.setCreatedBy("System");
        designation.setStatus(0);
        return designationRepo.save(designation);
    }

    @Override
    public Designation updateDesignation(Designation designation,Integer designationId) {
        LOGGER.info("Updating an existing Designation");
        if(!isDesignationExist(designationId)) {
            LOGGER.error("update() - Designation not found with the given Id: {} ", designationId);
            throw new ResourceNotFoundException(String.format("Designation not found with the given Id: %s", designationId));
        }
        designation.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        designation.setModifiedBy("System");
       return designationRepo.save(designation);
    }

    @Override
    public List<Designation> designationList() {
        LOGGER.info("Fetching all DesignationList");

        return designationRepo.findAllDesignation();
    }

    @Override
    public Designation findDesignationById(Integer designationId) {
        LOGGER.info("Fetching howDidId by id");
        Optional<Designation> designation = designationRepo.findByDesignationIdAndStatus(designationId, 0);
        return designation.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Designation not found with the given Id: %s", designationId)));
    }

    @Transactional
    @Override
    public String deleteDesignationById(Integer designationId) {
        if(!isDesignationExist(designationId)) {
            LOGGER.error("deleteDesignationById() - Designation not found with the given Id: {} ", designationId);
            throw new ResourceNotFoundException(String.format("Designation not found with the given Id: %s", designationId));
        }
        try{
            designationRepo.deleteDesignationById(designationId);
        } catch (Exception ex){
            LOGGER.error("deleteDesignationById() - Unable to delete Designation with the given Id: {} ", designationId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Designation with the given Id: %s", designationId), errorResponse);
        }

        return "Designation deleted successfully!";
    }

    private boolean isDesignationExist(Integer designationId){
        return designationRepo.findByDesignationIdAndStatus(designationId, 0).isPresent();
    }


}
