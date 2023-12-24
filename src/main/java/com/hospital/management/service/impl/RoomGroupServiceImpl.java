package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RoomGroup;
import com.hospital.management.repositary.RoomGroupRepo;
import com.hospital.management.service.RoomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomGroupServiceImpl implements RoomGroupService {

    @Autowired
    RoomGroupRepo roomGroupRepo;
    @Override
    public void save(RoomGroup roomGroup) {
        roomGroupRepo.save(roomGroup);
    }

    @Override
    public void update(RoomGroup roomGroup) {
        roomGroupRepo.save(roomGroup);
    }

    @Override
    public List<RoomGroup> roomGroupList() {
        return roomGroupRepo.findAll();
    }
}
