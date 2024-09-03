package com.hospital.management.service.impl;


import com.hospital.management.entities.commom.NurseStation;
import com.hospital.management.entities.response.NurseStationSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.NurseStationRepo;
import com.hospital.management.service.NurseStationService;
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
public class NurseStationServiceImpl implements NurseStationService {
//public class CountryServiceImpl implements CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NurseStationServiceImpl.class);

    @Autowired
    private NurseStationRepo nurseStationRepo;




    @Override
    public NurseStationSearchResult getAllNurseStation(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all NurseStatioms");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<NurseStation> nurseStationPages = nurseStationRepo.findAllNurseStations(search, pageable);

        return mapToNurseStationSearchResult(pageNo, pageSize, nurseStationPages.getContent());
    }
    private NurseStationSearchResult mapToNurseStationSearchResult(int pageNo, int pageSize, List<NurseStation> nurseStations) {
        NurseStationSearchResult nurseStationSearchResult = new NurseStationSearchResult();
        Long totalPages = (long) (nurseStations.size() % pageSize == 0 ? nurseStations.size() / pageSize : nurseStations.size() / pageSize+1);
        nurseStationSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) nurseStations.size(), totalPages, pageNo, pageSize));
        nurseStationSearchResult.setData(nurseStations);

        return nurseStationSearchResult;
    }

    @Override
    public NurseStation findNurseStationId(Long nurseStationId) {
        LOGGER.info("Fetching NurseStation by id");
        Optional<NurseStation> nurseStation = nurseStationRepo.findByNurseStationIdAndStatus(nurseStationId, 0);
        return nurseStation.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Country not found with the given Id: %s", nurseStationId)));
    }

    @Override
    public NurseStation saveNurseStation(NurseStation nurseStation) {
        LOGGER.info("Creating a new NurseStation");
        Long maxId = nurseStationRepo.getMaxId();
        nurseStation.setNurseStationCode("NS"+(maxId == null ? 1 : maxId+1));
        nurseStation.setCreatedBy("System");
        nurseStation.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        nurseStation.setStatus(0);
        return nurseStationRepo.save(nurseStation);
    }


    @Override
    public NurseStation updatenurseStation(NurseStation nurseStation, Long nurseStationId) {
        if(!isNurseStationExist(nurseStationId)) {
            LOGGER.error("updateNurseStation() - Given nurseStationId is not exist");
            throw new ResourceNotFoundException(String.format("NurseStation not found with the given Id: %s", nurseStationId));
        }
        nurseStation.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        nurseStation.setModifiedBy("System");
        return nurseStationRepo.save(nurseStation);
    }

    @Transactional
     @Override
    public String deleteNurseStationById(Long nurseStationId) {
        if(!isNurseStationExist(nurseStationId)) {
            LOGGER.error("deleteNurseStationById() - Given NurseStationId is not exist");
            throw new ResourceNotFoundException(String.format("NurseStation not found with the given Id: %s", nurseStationId));
        }
        try{
            nurseStationRepo.deleteNurseStationId(nurseStationId);
        } catch (Exception ex){
            LOGGER.error("deleteNurseStationById() - Unable to delete Nurse station with the given Id: {}", nurseStationId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete Nurse Station with the given Id: %s", nurseStationId), errorResponse);
        }

        return "Country deleted successfully!";
    }

    private boolean isNurseStationExist(Long nurseStationId){
        return nurseStationRepo.findByNurseStationIdAndStatus(nurseStationId, 0).isPresent();
    }
}
