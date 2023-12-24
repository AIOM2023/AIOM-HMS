package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RoomGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomGroupRepo extends JpaRepository<RoomGroup,Integer> {
}
