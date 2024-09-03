package com.hospital.management.service.impl;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.admin.Referral;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.entities.response.ReferralSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.ReferralRepo;
import com.hospital.management.service.ReferralService;
import com.hospital.management.utils.HmsCommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        referral.setCreatedBy("System");
        referral.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
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
        referral.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        referral.setModifiedBy("System");
        return referralRepo.save(referral);
    }

  /*  @Override
    public List<Referral> getAllReferrals(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all Referrals");
        return referralRepo.findAllReferrals();
    }*/

    @Override
    public ReferralSearchResult getAllReferrals(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all Referrals");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Referral> referralPages = referralRepo.findAllReferrals(search, pageable);

        return mapToReferralSearchResult(pageNo, pageSize, referralPages.getContent());
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

    private ReferralSearchResult mapToReferralSearchResult(int pageNo, int pageSize, List<Referral> referrals) {
        ReferralSearchResult referralSearchResult = new ReferralSearchResult();
        Long totalPages = (long) (referrals.size() % pageSize == 0 ? referrals.size() / pageSize : referrals.size() / pageSize+1);
        referralSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) referrals.size(), totalPages, pageNo, pageSize));
        referralSearchResult.setData(referrals);

        return referralSearchResult;
    }
}
