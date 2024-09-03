package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.HowDid;
import com.hospital.management.entities.response.HowDidSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.HowDidRepo;
import com.hospital.management.service.HowDidService;
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
public class HowDidServiceImpl implements HowDidService {

    @Autowired
    HowDidRepo howDidRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(HowDidServiceImpl.class);

    @Override
    public HowDid saveHowDid(HowDid howDid) {
        LOGGER.info("Creating a new HowDid");
        Long maxId = howDidRepo.getMaxId();

        howDid.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        howDid.setCreatedBy("System");
        howDid.setStatus(0);
      return howDidRepo.save(howDid);
    }

    @Override
    public HowDid updateHowDid(HowDid howDid,Long howDidId) {
        LOGGER.info("Updating an existing Tariff");
        if(!isHowDidExist(howDidId)) {
            LOGGER.error("update() - HowDid not found with the given Id: {} ", howDidId);
            throw new ResourceNotFoundException(String.format("HowDid not found with the given Id: %s", howDidId));
        }
        howDid.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        howDid.setModifiedBy("System");
        return howDidRepo.save(howDid);
    }

    @Override
    public HowDidSearchResult howDidList(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all HowDidList");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<HowDid> howDidPages = howDidRepo.findAllHowDidList(search, pageable);
        return mapToHowDidSearchResult(pageNo, pageSize, howDidPages.getContent());
    }
    private HowDidSearchResult mapToHowDidSearchResult(int pageNo, int pageSize, List<HowDid> howDid) {
        HowDidSearchResult howDidSearchResult = new HowDidSearchResult();
        Long totalPages = (long) (howDid.size() % pageSize == 0 ? howDid.size() / pageSize : howDid.size() / pageSize+1);
        howDidSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) howDid.size(), totalPages, pageNo, pageSize));
        howDidSearchResult.setData(howDid);
        return howDidSearchResult;
        }



    @Override
    public HowDid findHowDidByHowDidId(Long howDidId) {
        LOGGER.info("Fetching howDidId by id");
        Optional<HowDid> howDid = howDidRepo.findByHowDidIdAndStatus(howDidId, 0);
        return howDid.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Tariff not found with the given Id: %s", howDidId)));
    }

    @Transactional
    @Override
    public String deleteHowDidByHowDidId(Long howDidId) {
        if(!isHowDidExist(howDidId)) {
            LOGGER.error("deleteHowDidById() - HowDid not found with the given Id: {} ", howDidId);
            throw new ResourceNotFoundException(String.format("HowDid not found with the given Id: %s", howDidId));
        }
        try{
            howDidRepo.deleteHowDidById(howDidId);
        } catch (Exception ex){
            LOGGER.error("deleteHowDidById() - Unable to delete Tariff with the given Id: {} ", howDidId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete HowDid with the given Id: %s", howDidId), errorResponse);
        }

        return "HowDid deleted successfully!";
    }



    private boolean isHowDidExist(Long howDidId){
        return howDidRepo.findByHowDidIdAndStatus(howDidId, 0).isPresent();
    }
}
