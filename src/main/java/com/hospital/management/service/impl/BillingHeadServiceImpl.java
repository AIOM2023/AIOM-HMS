package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.BillingHeadRepo;
import com.hospital.management.service.BillingHeadService;
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
public class BillingHeadServiceImpl implements BillingHeadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BillingHeadServiceImpl.class);

    @Autowired
    private BillingHeadRepo billingHeadRepo;


    @Override
    public List<BillingHead> getAllBillingHeads() {
        LOGGER.info("Fetching all Billing Heads");
        return billingHeadRepo.findAllBillingHeads();
    }

    @Override
    public BillingHead findBillingHeadById(Long billingHeadId) {
        LOGGER.info("Fetching Billing Head by id");
        Optional<BillingHead> billingHead = billingHeadRepo.findByBillingHeadIdAndStatus(billingHeadId, 0);
        return billingHead.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Billing Head not found with the given Id: %s", billingHeadId)));
    }

    @Override
    public BillingHead saveBillingHead(BillingHead billingHead) {
        LOGGER.info("Creating a new BillingHead");

        Long maxId = billingHeadRepo.getMaxId();
        billingHead.setBillingHeadCode("BH-"+(maxId == null ? 1 : maxId+1));

        billingHead.setCreatedBy("System");
        billingHead.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        billingHead.setStatus(0);
        return billingHeadRepo.save(billingHead);
    }

    @Override
    public BillingHead updateBillingHead(BillingHead billingHead, Long billingHeadId) {
        if(!isBillingHeadExist(billingHeadId)) {
            LOGGER.error("updateBillingHead() - Given billingHeadId is not exist");
            throw new ResourceNotFoundException(String.format("BillingHead not found with the given Id: %s", billingHeadId));
        }
        billingHead.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        billingHead.setModifiedBy("System");

        return billingHeadRepo.save(billingHead);
    }

    @Override
    @Transactional
    public String deleteBillingHeadById(Long billingHeadId) {
        if(!isBillingHeadExist(billingHeadId)) {
            LOGGER.error("deleteBillingHeadById() - Given billingHeadId is not exist");
            throw new ResourceNotFoundException(String.format("BillingHead not found with the given Id: %s", billingHeadId));
        }
        try{
            billingHeadRepo.deleteBillingHeadById(billingHeadId);
        } catch (Exception ex){
            LOGGER.error("deleteBillingHeadById() - Unable to delete BillingHead with the given Id: {}", billingHeadId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete BillingHead with the given Id: %s", billingHeadId), errorResponse);
        }

        return "BillingHead deleted successfully!";
    }

    private boolean isBillingHeadExist(Long billingHeadId){
        return billingHeadRepo.findByBillingHeadIdAndStatus(billingHeadId, 0).isPresent();
    }
}
