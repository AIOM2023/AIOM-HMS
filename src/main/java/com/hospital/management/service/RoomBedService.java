package com.hospital.management.service;

import com.hospital.management.entities.commom.RoomBed;
import com.hospital.management.entities.response.RoomBedSearchResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomBedService {

    RoomBedSearchResult getAllRoomBeds(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    RoomBed findRoomBedById(Long roomBedId);

    RoomBed saveRoomBed(RoomBed roomBed);

    RoomBed updateRoomBed(RoomBed roomBed, Long roomBedId);

    @Transactional
    String deleteRoomBedById(Long roomBedId);

    List<String> getAllRoomGroupCodes();
}
