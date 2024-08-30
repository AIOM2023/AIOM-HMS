package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.ConsultChargeRepo;
import com.hospital.management.service.ConsultChargeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultChargeServiceImpl implements ConsultChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsultChargeService.class);
    private static final String CONSULT_CHARGE_NOT_FOUND = "ConsultCharge not found with the given Id: %s";
    @Autowired
    ConsultChargeRepo consultChargeRepo;
    @Override
    public ConsultCharge saveConsultCharge(ConsultCharge consultCharge) {
        LOGGER.info("Creating a new consultCharge");

        Long maxId = consultChargeRepo.getMaxId();
        consultCharge.setConsultCode("CONCOD-"+(maxId == null ? 1 : maxId+1));

        consultCharge.setStatus(0);
        return consultChargeRepo.save(consultCharge);
    }

    @Override
    public ConsultCharge updateConsultCharge(ConsultCharge consultCharge, Long consultId) {
        LOGGER.info("Updating an existing ConsultCharge");
        if(!isConsultChargeExist(consultId)) {
            LOGGER.error("update() - ConsultCharge not found with the given Id: {} ", consultId);
            throw new ResourceNotFoundException(String.format(CONSULT_CHARGE_NOT_FOUND, consultId));
        }
        consultCharge.setModifiedBy("System");

        return consultChargeRepo.save(consultCharge);
    }

    @Override
    public ConsultCharge findConsultChargeById(Long consultId) {
        LOGGER.info("Fetching ConsultCharge by id");
        Optional<ConsultCharge> consultCharge = consultChargeRepo.findByConsultIdAndStatus(consultId, 0);
        return consultCharge.orElseThrow(() ->
                new ResourceNotFoundException(String.format(CONSULT_CHARGE_NOT_FOUND, consultId)));
    }

    @Transactional
    @Override
    public String deleteConsultChargeById(Long consultId) {
        if(!isConsultChargeExist(consultId)) {
            LOGGER.error("deleteConsultChargeById() - ConsultCharge not found with the given Id: {} ", consultId);
            throw new ResourceNotFoundException(String.format(CONSULT_CHARGE_NOT_FOUND, consultId));
        }
        try{
            consultChargeRepo.deleteConsultChargeById(consultId);
        } catch (Exception ex){
            LOGGER.error("deleteConsultChargeById() - Unable to delete ConsultCharge with the given Id: {} ", consultId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete ConsultCharge with the given Id: %s", consultId), errorResponse);
        }

        return "ConsultCharge deleted successfully!";
    }
    @Override
    public List<ConsultCharge> consultChargeList() {
        LOGGER.info("find all ConsultCharge List");
        return  consultChargeRepo.findAll();
    }

    private boolean isConsultChargeExist(Long consultId){
        return consultChargeRepo.findByConsultIdAndStatus(consultId, 0).isPresent();
    }
}
