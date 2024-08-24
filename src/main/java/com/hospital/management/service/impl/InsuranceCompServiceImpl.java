package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeSummary;
import com.hospital.management.entities.commom.InsuranceComp;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.InsuranceCompRepo;
import com.hospital.management.service.InsuranceCompService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceCompServiceImpl implements InsuranceCompService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsuranceCompServiceImpl.class);
    private static final String INSURANCE_COMP_NOT_FOUND = "Insurance company not found with the given Id: %s";
    @Autowired
    InsuranceCompRepo insuranceCompRepo;

    @Override
    public InsuranceComp saveInsuranceComp(InsuranceComp insuranceComp) {

        LOGGER.info("Creating a new insurance company");

        Long maxId = insuranceCompRepo.getMaxId();
        return insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public InsuranceComp updateInsuranceComp(InsuranceComp insuranceComp,Integer insComId) {
        return insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public List<InsuranceComp> insuranceCompList() {
        LOGGER.info("find all insurance company List");
        return insuranceCompRepo.findAll();
    }

    @Override
    public InsuranceComp findInsuranceCompByInsComId(Integer insComId) {
        LOGGER.info("Fetching insurance company by id");
        Optional<InsuranceComp> insuranceComp = insuranceCompRepo.findByInsuranceCompIdAndStatus(insComId, 0);
        return insuranceComp.orElseThrow(() ->
                new ResourceNotFoundException(String.format(INSURANCE_COMP_NOT_FOUND, insComId)));
    }

    @Override
    public String deleteInsuranceCompByInsComId(Integer insComId) {
        if(!isInsuranceCompPresent(insComId)) {
            LOGGER.error("deleteInsuranceCompByInsComId() - Insurance company not found with the given Id: {} ", insComId);
            throw new ResourceNotFoundException(String.format(INSURANCE_COMP_NOT_FOUND, insComId));
        }
        try{
            insuranceCompRepo.deleteInsuranceCompIdById(insComId);
        } catch (Exception ex){
            LOGGER.error("deleteDischargeSummaryById() - Unable to delete DischargeSummary with the given Id: {} ", insComId);
            ErrorResponse errorResponse = new ErrorResponse();//"500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
            throw new HmsBusinessException(String.format("Unable to delete DischargeSummary with the given Id: %s", insComId), errorResponse);
        }
        return "";
    }

    private boolean isInsuranceCompPresent(Integer insComId){
        return insuranceCompRepo.findByInsuranceCompIdAndStatus(insComId, 0).isPresent();
    }
}
