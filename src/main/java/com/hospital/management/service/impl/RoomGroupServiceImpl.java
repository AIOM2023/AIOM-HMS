package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RoomGroup;
import com.hospital.management.entities.response.RoomGroupSearchResult;
import com.hospital.management.exceptions.HmsBusinessException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.payload.ErrorResponse;
import com.hospital.management.repositary.RoomGroupRepo;
import com.hospital.management.service.RoomGroupService;
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
public class RoomGroupServiceImpl implements RoomGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomGroupServiceImpl.class);

    @Autowired
    private RoomGroupRepo roomGroupRepo;

    @Override
    public RoomGroupSearchResult getAllRoomGroups(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all RoomGroups");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<RoomGroup> pages = roomGroupRepo.findAllRoomGroups(search, pageable);

        return mapToRoomGroupSearchResult(pageNo, pageSize, pages.getContent());
    }

    private RoomGroupSearchResult mapToRoomGroupSearchResult(int pageNo, int pageSize, List<RoomGroup> roomGroups) {
        RoomGroupSearchResult roomGroupSearchResult = new RoomGroupSearchResult();
        Long totalPages = (long) (roomGroups.size() % pageSize == 0 ? roomGroups.size() / pageSize : roomGroups.size() / pageSize+1);
        roomGroupSearchResult.setMetaData(HmsCommonUtil.getMetaData((long) roomGroups.size(), totalPages, pageNo, pageSize));
        roomGroupSearchResult.setData(roomGroups);

        return roomGroupSearchResult;
    }
    /*@Override
    public CountrySearchResult getAllCountries(String search, int pageNo, int pageSize, String sortBy, String sortOrder) {
        LOGGER.info("Fetching all countries");
        Sort sort = Sort.by(Sort.Direction.fromString(sortOrder), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Country> pages = countryRepo.findAllCountries(search, pageable);

        return mapToCountrySearchResult(pageNo, pageSize, pages.getContent());
    }

private CountrySearchResult mapToCountrySearchResult(int pageNo, int pageSize, List<Country> countries) {
        CountrySearchResult countrySearchResult = new CountrySearchResult();
        Long totalPages = (long) (countries.size() % pageSize == 0 ? countries.size() / pageSize : countries.size() / pageSize+1);
        countrySearchResult.setMetaData(HmsCommonUtil.getMetaData((long) countries.size(), totalPages, pageNo, pageSize));
        countrySearchResult.setData(countries);

        return countrySearchResult;
}*/

    @Override
    public RoomGroup findRoomGroupById(Long roomGroupId) {
        LOGGER.info("Fetching Room Group by roomGroupId");
        Optional<RoomGroup> roomGroup = roomGroupRepo.findByRoomGroupIdAndStatus(roomGroupId, 0);
        return roomGroup.orElseThrow(() ->
                new ResourceNotFoundException(String.format("roomGroup not found with the given Id: %s", roomGroupId)));
    }

    @Override
    public RoomGroup saveRoomGroup(RoomGroup roomGroup) {
        LOGGER.info("Creating a new RoomGroup");

        Long maxId = roomGroupRepo.getMaxId();
        roomGroup.setRoomGroupCode("RG-"+(maxId == null ? 1 : maxId+1));

        roomGroup.setCreatedBy("System");
        roomGroup.setCreatedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        roomGroup.setStatus(0);
        return roomGroupRepo.save(roomGroup);
    }

    @Override
    public RoomGroup updateRoomGroup(RoomGroup roomGroup, Long roomGroupId) {
        if(!isRoomGroupExist(roomGroupId)) {
            LOGGER.error("updateRoomGroup() - Given roomGroupId is not exist");
            throw new ResourceNotFoundException(String.format("RoomGroup not found with the given Id: %s", roomGroupId));
        }
        roomGroup.setModifiedDate(HmsCommonUtil.getSystemDateInUTCFormat());
        roomGroup.setModifiedBy("System");

        return roomGroupRepo.save(roomGroup);
    }


    @Transactional
    @Override
    public String deleteRoomGroupById(Long roomGroupId) {
        if(!isRoomGroupExist(roomGroupId)) {
            LOGGER.error("deleteRoomGroupById() - Given roomGroupId is not exist");
            throw new ResourceNotFoundException(String.format("RoomGroup not found with the given Id: %s", roomGroupId));
        }
        try{
            roomGroupRepo.deleteRoomGroupById(roomGroupId);
        } catch (Exception ex){
            LOGGER.error("deleteRoomGroupById() - Unable to delete RoomGroup with the given Id: {}", roomGroupId);
            ErrorResponse errorResponse = new ErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            throw new HmsBusinessException(String.format("Unable to delete RoomGroup with the given Id: %s", roomGroupId), errorResponse);
        }

        return "RoomGroup deleted successfully!";
    }

    @Override
    public List<String> getAllRoomGroupCodes() {
        return roomGroupRepo.findAllActiveRoomGroupCodes();
    }


    private boolean isRoomGroupExist(Long roomGroupId){
        return roomGroupRepo.findByRoomGroupIdAndStatus(roomGroupId, 0).isPresent();
    }
}
