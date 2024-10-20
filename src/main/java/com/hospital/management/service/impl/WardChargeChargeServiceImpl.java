package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.WardCharge;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.WardChargeRepo;
import com.hospital.management.service.WardChargeService;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardChargeChargeServiceImpl implements WardChargeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateServiceImpl.class);

    @Autowired
    private WardChargeRepo wardChargeRepo;

    @Override
    public List<WardCharge> getAllWards() {
        LOGGER.info("Fetching all WardCharges");
        return wardChargeRepo.findAllWards();
    }

    @Override
    public WardCharge findWardById(Long wardId) {
        LOGGER.info("Fetching state by id");
        Optional<WardCharge> state = wardChargeRepo.findByWardIdAndStatus(wardId, 0);
        return state.orElseThrow(() ->
                new ResourceNotFoundException(String.format("WardCharges not found with the given Id: %s", wardId)));
    }

    @Override
    public WardCharge saveWard(WardCharge wardCharge) {
        LOGGER.info("Creating a new WardCharges");

        Long maxId = wardChargeRepo.getMaxId();
        wardCharge.setWardCode("WD-"+(maxId == null ? 1 : maxId+1));

        wardCharge.setCreatedBy("System");
        wardCharge.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        wardCharge.setStatus(0);
        return wardChargeRepo.save(wardCharge);
    }

    @Override
    public WardCharge updateWard(WardCharge wardCharge, Long wardId) {
        if(!isWardExist(wardId)) {
            LOGGER.error("updateWardCharges() - Given wardID is not exist");
            throw new ResourceNotFoundException(String.format("WardCharges not found with the given Id: %s", wardId));
        }
        wardCharge.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        wardCharge.setModifiedBy("System");
        return wardChargeRepo.save(wardCharge);
    }

    @Transactional
    @Override
    public String deleteWardById(Long wardId) {
        if(!isWardExist(wardId)) {
            LOGGER.error("deleteWardChargesById() - Given wardId is not exist");
            throw new ResourceNotFoundException(String.format("WardCharges not found with the given Id: %s", wardId));
        }
        try{
            wardChargeRepo.deleteWardById(wardId);
        } catch (Exception ex){
            LOGGER.error("deleteWardChargesById() - Unable to delete WardCharges with the given Id: {}", wardId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete WardCharges with the given Id: %s", wardId), errorResponse);
        }

        return "WardCharges deleted successfully!";
    }

    private boolean isWardExist(Long wardId){
        return wardChargeRepo.findByWardIdAndStatus(wardId, 0).isPresent();
    }
}
