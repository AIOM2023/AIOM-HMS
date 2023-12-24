package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RoomBed;
import com.hospital.management.repositary.RoomBedRepo;
import com.hospital.management.service.RoomBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomBedServiceImpl implements RoomBedService {

    @Autowired
    RoomBedRepo roomBedRepo;
    @Override
    public void save(RoomBed roomBed) {
        roomBedRepo.save(roomBed);
    }

    @Override
    public void update(RoomBed roomBed) {
        roomBedRepo.save(roomBed);
    }

    @Override
    public List<RoomBed> roomBedList() {
        return roomBedRepo.findAll();
    }
}
