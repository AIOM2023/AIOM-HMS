package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.response.DischargeFormatSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.DischargeFormatRepo;
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
public class DischargeFormatImpl implements DischargeFormatService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DischargeFormatImpl.class);
    private static final String REFERRAL_NOT_FOUND = "DischargeFormat not found with the given Id: %s";
    @Autowired
    DischargeFormatRepo dischargeFormatRepo;

    @Override
    public DischargeFormat saveDischargeFormat(DischargeFormat dischargeFormat) {
        LOGGER.info("Creating a new DischargeFormat");

        Long maxId = dischargeFormatRepo.getMaxId();
        dischargeFormat.setDisfmtCode("DF-"+(maxId == null ? 1 : maxId+1));
        dischargeFormat.setCreatedBy("System");
        dischargeFormat.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        dischargeFormat.setStatus(0);
        return dischargeFormatRepo.save(dischargeFormat);
    }


    @Override
    public DischargeFormat updateDischargeFormat(DischargeFormat dischargeFormat,Long discFmtId) {
        LOGGER.info("Updating an existing DischargeFormat");
        if(!isDischargeFormatExist(discFmtId)) {
            LOGGER.error("update() - DischargeFormat not found with the given Id: {} ", discFmtId);
            throw new ResourceNotFoundException(String.format(REFERRAL_NOT_FOUND, discFmtId));
        }
        dischargeFormat.setModifiedBy("System");
        dischargeFormat.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        return dischargeFormatRepo.save(dischargeFormat);
    }

    @Override
    public DischargeFormatSearchResult dischargeFormatList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("find all dischargeFormat List");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<DischargeFormat> dischargeFormatPages = dischargeFormatRepo.findAllDischargeFormat(search, pageable);
        return mapToDischargeFormatSearchResult(pageNo, pageSize, dischargeFormatPages.getContent());
    }
    private DischargeFormatSearchResult mapToDischargeFormatSearchResult(int pageNo, int pageSize, List<DischargeFormat> dischargeFormat) {
        DischargeFormatSearchResult dischargeFormatSearchResult = new DischargeFormatSearchResult();
        Long totalPages = (long) (dischargeFormat.size() % pageSize == 0 ? dischargeFormat.size() / pageSize : dischargeFormat.size() / pageSize+1);
        dischargeFormatSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) dischargeFormat.size(), totalPages, pageNo, pageSize));
        dischargeFormatSearchResult.setData(dischargeFormat);

        return dischargeFormatSearchResult;
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
