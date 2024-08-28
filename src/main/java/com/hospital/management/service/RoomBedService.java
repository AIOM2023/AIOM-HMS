package com.hospital.management.service;

import com.hospital.management.entities.commom.RoomBed;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomBedService {

    List<RoomBed> getAllRoomBeds();

    RoomBed findRoomBedById(Long roomBedId);

    RoomBed saveRoomBed(RoomBed roomBed);

    RoomBed updateRoomBed(RoomBed roomBed, Long roomBedId);

    @Transactional
    String deleteRoomBedById(Long roomBedId);

    List<String> getAllRoomGroupCodes();
}
