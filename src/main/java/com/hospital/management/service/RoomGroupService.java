package com.hospital.management.service;


import com.hospital.management.entities.commom.RoomGroup;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomGroupService {

    List<RoomGroup> getAllRoomGroups();

    RoomGroup findRoomGroupById(Long roomGroupId);

    RoomGroup saveRoomGroup(RoomGroup roomGroup);

    RoomGroup updateRoomGroup(RoomGroup roomGroup, Long roomGroupId);

    @Transactional
    String deleteRoomGroupById(Long roomGroupId);

    List<String> getAllRoomGroupCodes();
}
