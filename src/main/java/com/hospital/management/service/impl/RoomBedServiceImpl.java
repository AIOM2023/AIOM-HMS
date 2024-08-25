package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RoomBed;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.RoomBedRepo;
import com.hospital.management.service.RoomBedService;
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
public class RoomBedServiceImpl implements RoomBedService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomBedServiceImpl.class);

    @Autowired
    private RoomBedRepo roomBedRepo;

    @Override
    public List<RoomBed> getAllRoomBeds() {
        LOGGER.info("Fetching all RoomBeds");
        return roomBedRepo.findAllRoomBeds();
    }

    @Override
    public RoomBed findRoomBedById(Long roomBedId) {
        LOGGER.info("Fetching Room Group by roomBedId");
        Optional<RoomBed> roomBed = roomBedRepo.findByRoomBedIdAndStatus(roomBedId, 0);
        return roomBed.orElseThrow(() ->
                new ResourceNotFoundException(String.format("roomBed not found with the given Id: %s", roomBedId)));
    }

    @Override
    public RoomBed saveRoomBed(RoomBed roomBed) {
        LOGGER.info("Creating a new RoomBed");

        Long maxId = roomBedRepo.getMaxId();
        roomBed.setRoomCode("RB-"+(maxId == null ? 1 : maxId+1));

        roomBed.setCreatedBy("System");
        roomBed.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        roomBed.setStatus(0);
        return roomBedRepo.save(roomBed);
    }

    @Override
    public RoomBed updateRoomBed(RoomBed roomBed, Long roomBedId) {
        if(!isRoomBedExist(roomBedId)) {
            LOGGER.error("updateRoomBed() - Given roomBedId is not exist");
            throw new ResourceNotFoundException(String.format("RoomBed not found with the given Id: %s", roomBedId));
        }
        roomBed.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        roomBed.setModifiedBy("System");

        return roomBedRepo.save(roomBed);
    }

    @Transactional
    @Override
    public String deleteRoomBedById(Long roomBedId) {
        if(!isRoomBedExist(roomBedId)) {
            LOGGER.error("deleteRoomBedById() - Given roomGroupId is not exist");
            throw new ResourceNotFoundException(String.format("RoomBed not found with the given Id: %s", roomBedId));
        }
        try{
            roomBedRepo.deleteRoomBedById(roomBedId);
        } catch (Exception ex){
            LOGGER.error("deleteRoomBedById() - Unable to delete RoomBed with the given Id: {}", roomBedId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete RoomBed with the given Id: %s", roomBedId), errorResponse);
        }

        return "RoomBed deleted successfully!";
    }

    @Override
    public List<String> getAllRoomGroupCodes() {
        return List.of();
    }

    private boolean isRoomBedExist(Long roomBedId){
        return roomBedRepo.findByRoomBedIdAndStatus(roomBedId, 0).isPresent();
    }
}
