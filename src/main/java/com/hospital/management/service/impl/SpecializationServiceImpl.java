package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Specialization;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.SpecializationRepo;
import com.hospital.management.service.SpecializationService;
import com.hospital.management.utils.HmsCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecializationServiceImpl.class);

    @Autowired
    private SpecializationRepo specializationRepo;

    @Override
    public List<Specialization> getAllSpecializations() {
        LOGGER.info("Fetching all Specializations");
        return specializationRepo.findAllSpecializations();
    }

    @Override
    public Specialization findSpecializationById(Long specializationId) {
        LOGGER.info("Fetching Specialization by specializationId");
        Optional<Specialization> specialization = specializationRepo.findBySpecializationIdAndStatus(specializationId, 0);
        return specialization.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Specialization not found with the given Id: %s", specializationId)));
    }

    @Override
    public Specialization saveSpecialization(Specialization specialization) {
        LOGGER.info("Creating a new Specialization");

        Long maxId = specializationRepo.getMaxId();
        specialization.setSpecializationCode("SP-"+(maxId == null ? 1 : maxId+1));

        specialization.setCreatedBy("System");
        specialization.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        specialization.setStatus(0);
        return specializationRepo.save(specialization);
    }

    @Override
    public Specialization updateSpecialization(Specialization specialization, Long specializationId) {
        if(!isSpecializationExist(specializationId)) {
            LOGGER.error("updateSpecialization() - Given specializationId is not exist");
            throw new ResourceNotFoundException(String.format("Specialization not found with the given Id: %s", specializationId));
        }
        specialization.setModified_date(HmsCommonUtil.getSystemDateInUTCFormat());
        specialization.setModified_by("System");

        return specializationRepo.save(specialization);
    }

    @Transactional
    @Override
    public String deleteSpecializationById(Long specializationId) {
        if(!isSpecializationExist(specializationId)) {
            LOGGER.error("deleteSpecializationById() - Given serviceGroupId is not exist");
            throw new ResourceNotFoundException(String.format("Specialization not found with the given Id: %s", specializationId));
        }
        try{
            specializationRepo.deleteSpecializationById(specializationId);
        } catch (Exception ex){
            LOGGER.error("deleteSpecializationById() - Unable to delete Specialization with the given Id: {}", specializationId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Specialization with the given Id: %s", specializationId), errorResponse);
        }

        return "Specialization deleted successfully!";
    }

    private boolean isSpecializationExist(Long specializationId){
        return specializationRepo.findBySpecializationIdAndStatus(specializationId, 0).isPresent();
    }
}
