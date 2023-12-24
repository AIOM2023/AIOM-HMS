package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RoomBed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomBedRepo extends JpaRepository<RoomBed,Integer> {
}
