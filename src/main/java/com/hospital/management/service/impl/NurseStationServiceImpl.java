package com.hospital.management.service.impl;


import com.hospital.management.entities.commom.NurseStation;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.NurseStationRepo;
import com.hospital.management.utils.HmsCommonUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class NurseStationServiceimpl {


    private static final Logger LOGGER = LoggerFactory.getLogger(NurseStationService.class);

    @Autowired
    private NurseStationRepo nurseStationRepo;

    //@Override
    public List<NurseStation> getAllNurseStation() {
        LOGGER.info("Fetching all NurseStation details ");
        return nurseStationRepo.findAllNurseStations();
    }

    //@Override
    public NurseStation findNurseStationId(Integer nurseStationId) {
        LOGGER.info("Fetching NurseStation by id");
        Optional<NurseStation> nurseStation = nurseStationRepo.findByNurseStationIdAndStatus(nurseStationId, 0);
        return nurseStation.orElseThrow(() ->
                new ResourceNotFoundException(String.format("Country not found with the given Id: %s", nurseStationId)));
    }

  //  @Override
    public NurseStation saveNurseStation(NurseStation nurseStation) {
        LOGGER.info("Creating a new NurseStation");
        nurseStation.setCreatedBy("System");
        nurseStation.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        nurseStation.setStatus(0);
        return nurseStationRepo.save(nurseStation);
    }


 //   @Override
    public NurseStation updatenurseStation(NurseStation nurseStation, Integer nurseStationId) {
        if(!isNurseStationExist(nurseStationId)) {
            LOGGER.error("updateNurseStation() - Given nurseStationId is not exist");
            throw new ResourceNotFoundException(String.format("NurseStation not found with the given Id: %s", nurseStationId));
        }
        nurseStation.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        nurseStation.setModifiedBy("System");
        return nurseStationRepo.save(nurseStation);
    }

    @Transactional
   // @Override
    public String deleteNurseStationById(Integer nurseStationId) {
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

    private boolean isNurseStationExist(Integer nurseStationId){
        return nurseStationRepo.findByNurseStationIdAndStatus(nurseStationId, 0).isPresent();
    }
}
