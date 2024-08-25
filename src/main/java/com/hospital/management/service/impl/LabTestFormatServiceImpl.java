package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.LabTestFormat;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.LabTestFormatRepo;
import com.hospital.management.service.LabTestFormatService;
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
public class LabTestFormatServiceImpl implements LabTestFormatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LabTestFormatServiceImpl.class);

    @Autowired
    private LabTestFormatRepo labTestFormatRepo;

    @Override
    public List<LabTestFormat> getAllTestFormats() {
        LOGGER.info("Fetching all Test Formats");
        return labTestFormatRepo.findAllTestFormats();
    }

    @Override
    public LabTestFormat findTestFormatById(Long labTestFormatId) {
        LOGGER.info("Fetching Test Format by id");
        Optional<LabTestFormat> labTestFormat = labTestFormatRepo.findByTestFormatIdAndStatus(labTestFormatId, 0);
        return labTestFormat.orElseThrow(() ->
                new ResourceNotFoundException(String.format("LabTestFormat not found with the given Id: %s", labTestFormatId)));
    }

    @Override
    public LabTestFormat saveTestFormat(LabTestFormat labTestFormat) {
        LOGGER.info("Creating a new LabTestFormat");

        //Long maxId = labTestFormatRepo.getMaxId();
        labTestFormat.setCreatedBy("System");
        labTestFormat.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        labTestFormat.setStatus(0);
        return labTestFormatRepo.save(labTestFormat);
    }

    @Override
    public LabTestFormat updateTestFormat(LabTestFormat labTestFormat, Long labTestFormatId) {
        if(!isTestFormatExist(labTestFormatId)) {
            LOGGER.error("updateTestFormat() - Given labTestFormatId is not exist");
            throw new ResourceNotFoundException(String.format("LabTestFormat not found with the given Id: %s", labTestFormatId));
        }
        labTestFormat.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        labTestFormat.setModifiedBy("System");

        return labTestFormatRepo.save(labTestFormat);
    }

    @Override
    @Transactional
    public String deleteTestFormatById(Long labTestFormatId) {
        if(!isTestFormatExist(labTestFormatId)) {
            LOGGER.error("deleteTestFormatById() - Given labTestFormatId is not exist");
            throw new ResourceNotFoundException(String.format("LabTestFormat not found with the given Id: %s", labTestFormatId));
        }
        try{
            labTestFormatRepo.deleteTestFormatById(labTestFormatId);
        } catch (Exception ex){
            LOGGER.error("deleteTestFormatById() - Unable to delete LabTestFormat with the given Id: {}", labTestFormatId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete LabTestFormat with the given Id: %s", labTestFormatId), errorResponse);
        }

        return "BillingHead deleted successfully!";
    }

    private boolean isTestFormatExist(Long testFormatId){
        return labTestFormatRepo.findByTestFormatIdAndStatus(testFormatId, 0).isPresent();
    }
}
