package com.hospital.management.service.impl;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.ReferralRepo;
import com.hospital.management.service.ReferralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReferralServiceImpl implements ReferralService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryServiceImpl.class);
    private static final String REFERRAL_NOT_FOUND = "Referral not found with the given Id: %s";

    @Autowired
    private ReferralRepo referralRepo;

    @Override
    public Referral saveReferral(Referral referral) {
        LOGGER.info("Creating a new Referral");

        Long maxId = referralRepo.getMaxId();
        referral.setReferralCode("RF-"+(maxId == null ? 1 : maxId+1));

        referral.setStatus(0);
        return referralRepo.save(referral);
    }

    @Override
    public Referral updateReferral(Referral referral, Long referralId) {
        LOGGER.info("Updating an existing Referral");
        if(!isReferralExist(referralId)) {
            LOGGER.error("update() - Referral not found with the given Id: {} ", referralId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, referralId));
        }
        return referralRepo.save(referral);
    }

    @Override
    public List<Referral> getAllReferrals() {
        LOGGER.info("Fetching all Referrals");
        return referralRepo.findAllReferrals();
    }

    @Override
    public Referral findReferralById(Long referralId) {
        LOGGER.info("Fetching referral by id");
        Optional<Referral> referral = referralRepo.findByReferralIdAndStatus(referralId, 0);
        return referral.orElseThrow(() ->
                new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, referralId)));
    }

    @Transactional
    @Override
    public String deleteReferralById(Long referralId) {
        if(!isReferralExist(referralId)) {
            LOGGER.error("deleteReferralById() - Referral not found with the given Id: {} ", referralId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, referralId));
        }
        try{
            referralRepo.deleteReferralById(referralId);
        } catch (Exception ex){
            LOGGER.error("deleteTariffById() - Unable to delete Tariff with the given Id: {} ", referralId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Tariff with the given Id: %s", referralId), errorResponse);
        }

        return "Referral deleted successfully!";
    }

    private boolean isReferralExist(Long referralId){
        return referralRepo.findByReferralIdAndStatus(referralId, 0).isPresent();
    }
}
