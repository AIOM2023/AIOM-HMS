package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.InsuranceComp;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.InsuranceCompRepo;
import com.hospital.management.service.InsuranceCompService;
import jakarta.transaction.Transactional;
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
        insuranceComp.setInsComId((maxId == null ? 1 : maxId+1));
        insuranceComp.setStatus(0);
        return insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public InsuranceComp updateInsuranceComp(InsuranceComp insuranceComp,Long insComId) {
        LOGGER.info("Updating an existing Insurance Company");
        if(!isInsuranceCompPresent(insComId)) {
            LOGGER.error("update() - InsuranceCompany not found with the given Id: {} ", insComId);
            throw new ResourceNotFoundException(String.format(INSURANCE_COMP_NOT_FOUND, insComId));
        }
        insuranceComp.setModifiedBy("System");
        return insuranceCompRepo.save(insuranceComp);
    }

    @Override
    public List<InsuranceComp> insuranceCompList() {
        LOGGER.info("find all insurance company List");
        return insuranceCompRepo.findAll();
    }

    @Override
    public InsuranceComp findInsuranceCompByInsComId(Long insComId) {
        LOGGER.info("Fetching insurance company by id");
        Optional<InsuranceComp> insuranceComp = insuranceCompRepo.findByInsComIdAndStatus(insComId, 0);
        return insuranceComp.orElseThrow(() ->
                new ResourceNotFoundException(String.format(INSURANCE_COMP_NOT_FOUND, insComId)));
    }

    @Transactional
    @Override
    public String deleteInsuranceCompByInsComId(Long insComId) {
        if(!isInsuranceCompPresent(insComId)) {
            LOGGER.error("deleteInsuranceCompByInsComId() - Insurance company not found with the given Id: {} ", insComId);
            throw new ResourceNotFoundException(String.format(INSURANCE_COMP_NOT_FOUND, insComId));
        }
        try{
            insuranceCompRepo.deleteInsuranceCompIdById(insComId);
        } catch (Exception ex){
            LOGGER.error("deleteInsuranceCompByInsComId() - Unable to delete Insurance Company with the given Id: {} ", insComId);
            ErrorResponse errorResponse = new ErrorResponse();//"500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
            LOGGER.error("Stacktrace during delete exception {} ",ex.getStackTrace());
            throw new HmsBusinessException(String.format("Unable to delete Insurance Company with the given Id: %s", insComId), errorResponse);
        }
        return "Insurance Company deleted successfully!";
    }

    private boolean isInsuranceCompPresent(Long insComId){
        return insuranceCompRepo.findByInsComIdAndStatus(insComId, 0).isPresent();
    }
}
