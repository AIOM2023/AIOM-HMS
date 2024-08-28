package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.DischargeFormatRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DischargeFormatImpl implements DischargeFormatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DischargeFormatImpl.class);
    private static final String REFERRAL_NOT_FOUND = "DischargeSummary not found with the given Id: %s";
    @Autowired
    DischargeFormatRepo dischargeFormatRepo;

    @Override
    public DischargeFormat saveDischargeFormat(DischargeFormat dischargeFormat) {
        LOGGER.info("Creating a new DischargeFormat");

        Long maxId = dischargeFormatRepo.getMaxId();
        dischargeFormat.setDisfmtCode("DF-"+(maxId == null ? 1 : maxId+1));

        dischargeFormat.setStatus(0);
        return dischargeFormatRepo.save(dischargeFormat);
    }

  /*  @Override
    public void save(DischargeSummary dischargeSummary) {
        dischargeSummaryRepo.save(dischargeSummary);
    }
*/
    @Override
    public DischargeFormat updateDischargeFormat(DischargeFormat dischargeFormat,Long discFmtId) {
        LOGGER.info("Updating an existing DischargeFormat");
        if(!isDischargeFormatExist(discFmtId)) {
            LOGGER.error("update() - DischargeFormat not found with the given Id: {} ", discFmtId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, discFmtId));
        }
        dischargeFormat.setModifiedBy("System");
       // dischargeSummary.setModifiedDate();
        return dischargeFormatRepo.save(dischargeFormat);
    }

    @Override
    public List<DischargeFormat> dischargeFormatList() {
        LOGGER.info("find all dischargeFormat List");
        return dischargeFormatRepo.findAll();
    }
    @Override
    public DischargeFormat findDischargeFromatById(Long discFmtId) {
        LOGGER.info("Fetching DischargeFormat by id");
        Optional<DischargeFormat> dischargeFormat = dischargeFormatRepo.findByDiscFmtIdAndStatus(discFmtId, 0);
        return dischargeFormat.orElseThrow(() ->
                new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, discFmtId)));
    }

    @Transactional
    @Override
    public String deleteDischargeFormatById(Long discFmtId) {
        if(!isDischargeFormatExist(discFmtId)) {
            LOGGER.error("isDischargeFormatExist    () - DischargeFormat not found with the given Id: {} ", discFmtId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, discFmtId));
        }
        try{
            dischargeFormatRepo.deleteDischargeFormatById(discFmtId);
        } catch (Exception ex){
            LOGGER.error("deleteDischargeSummaryById() - Unable to delete DischargeFormat with the given Id: {} ", discFmtId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete DischargeFormat with the given Id: %s", discFmtId), errorResponse);
        }

        return "DischargeFormat deleted successfully!";
    }

    private boolean isDischargeFormatExist(Long discFmtId){
        return dischargeFormatRepo.findByDiscFmtIdAndStatus(discFmtId, 0).isPresent();
    }


}
