package com.hospital.management.service;


import com.hospital.management.entities.commom.RoomGroup;

import java.util.List;

public interface RoomGroupService {

    void save(RoomGroup roomGroup);

    void update(RoomGroup roomGroup);

    List<RoomGroup> roomGroupList();
}
