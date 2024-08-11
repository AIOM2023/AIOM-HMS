package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeSummary;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.DischargeSummaryRepo;
import com.hospital.management.service.DischargeSummaryService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DischargeServiceImpl implements DischargeSummaryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DischargeServiceImpl.class);
    private static final String REFERRAL_NOT_FOUND = "DischargeSummary not found with the given Id: %s";
    @Autowired
    DischargeSummaryRepo dischargeSummaryRepo;

    @Override
    public DischargeSummary saveDischargeSummary(DischargeSummary dischargeSummary) {
        LOGGER.info("Creating a new dischargeSummary");

        Long maxId = dischargeSummaryRepo.getMaxId();
        dischargeSummary.setDischargeNo("DS-"+(maxId == null ? 1 : maxId+1));

        dischargeSummary.setStatus(0);
        return dischargeSummaryRepo.save(dischargeSummary);
    }

  /*  @Override
    public void save(DischargeSummary dischargeSummary) {
        dischargeSummaryRepo.save(dischargeSummary);
    }
*/
    @Override
    public DischargeSummary updateDischargeSummary(DischargeSummary dischargeSummary,Long dischargeId) {
        LOGGER.info("Updating an existing DischargeSummary");
        if(!isDischargeSummaryExist(dischargeId)) {
            LOGGER.error("update() - DischargeSummary not found with the given Id: {} ", dischargeId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, dischargeId));
        }
        dischargeSummary.setModifiedBy("System");
       // dischargeSummary.setModifiedDate();
        return dischargeSummaryRepo.save(dischargeSummary);
    }

    @Override
    public List<DischargeSummary> dischargeSummaryList() {
        LOGGER.info("find all DischargeSummary List");
        return dischargeSummaryRepo.findAll();
    }
    @Override
    public DischargeSummary findDischargeSummaryById(Long dischargeId) {
        LOGGER.info("Fetching DischargeSummary by id");
        Optional<DischargeSummary> dischargeSummary = dischargeSummaryRepo.findByDischargeIdAndStatus(dischargeId, 0);
        return dischargeSummary.orElseThrow(() ->
                new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, dischargeId)));
    }

    @Transactional
    @Override
    public String deleteDischargeSummaryById(Long dischargeId) {
        if(!isDischargeSummaryExist(dischargeId)) {
            LOGGER.error("deleteDischargeSummaryById() - DischargeSummary not found with the given Id: {} ", dischargeId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, dischargeId));
        }
        try{
            dischargeSummaryRepo.deleteDischargeSummaryById(dischargeId);
        } catch (Exception ex){
            LOGGER.error("deleteDischargeSummaryById() - Unable to delete DischargeSummary with the given Id: {} ", dischargeId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete DischargeSummary with the given Id: %s", dischargeId), errorResponse);
        }

        return "DischargeSummary deleted successfully!";
    }

    private boolean isDischargeSummaryExist(Long dischargeId){
        return dischargeSummaryRepo.findByDischargeIdAndStatus(dischargeId, 0).isPresent();
    }


}
