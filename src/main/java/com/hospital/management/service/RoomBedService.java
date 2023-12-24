package com.hospital.management.service;

import com.hospital.management.entities.commom.RoomBed;

import java.util.List;

public interface RoomBedService {

    void save(RoomBed roomBed);

    void update(RoomBed roomBed);

    List<RoomBed> roomBedList();
}
