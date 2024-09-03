package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.entities.response.ReferralSearchResult;
import com.hospital.management.entities.response.RegistrationFeesSearchResult;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Long maxId = registrationFeesRepo.getMaxId();
        registrationFees.setRegFeeCode("RF"
                +(maxId == null ? 1 : maxId+1));

        registrationFees.setCreatedBy("System");
        registrationFees.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        registrationFees.setStatus(0);
       return registrationFeesRepo.save(registrationFees);
    }

    @Override
    public RegistrationFees updateregistrationFees(RegistrationFees registrationFees,Long regFeesId) {
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
    public RegistrationFeesSearchResult registrationFeesList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all RegistrationFees");
            Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            Page<RegistrationFees> pages = registrationFeesRepo.findAllRegistrationFees(search, pageable);

            return mapToRegistrationFeesSearchResult(pageNo, pageSize, pages.getContent());


    }

    @Override
    public RegistrationFees findRegistrationFeesId(Long regFeesId) {
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
    public String deleteRegistrationFeesById(Long regFeesId) {
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

    private boolean isRegistrationExist(Long regFeesId){
        return registrationFeesRepo.findByRegFeesIdAndStatus(regFeesId, 0).isPresent();
    }

    private RegistrationFeesSearchResult mapToRegistrationFeesSearchResult(int pageNo, int pageSize, List<RegistrationFees> registrationFeesList) {
        RegistrationFeesSearchResult registrationFeesSearchResult = new RegistrationFeesSearchResult();
        Long totalPages = (long) (registrationFeesList.size() % pageSize == 0 ? registrationFeesList.size() / pageSize : registrationFeesList.size() / pageSize + 1);
        registrationFeesSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) registrationFeesList.size(), totalPages, pageNo, pageSize));
        registrationFeesSearchResult.setData(registrationFeesList);

        return registrationFeesSearchResult;
    }
}
