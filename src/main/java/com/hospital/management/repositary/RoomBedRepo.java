package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RoomBed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomBedRepo extends JpaRepository<RoomBed,Long> {

    @Query(value = "SELECT * FROM room_bed rg WHERE rb.status = 0", nativeQuery = true)
    List<RoomBed> findAllRoomBeds();


    Optional<RoomBed> findByRoomBedIdAndStatus(Long roomBedId, Integer status);

    @Query(value = "UPDATE room_bed SET status = 1 WHERE room_bed_id = :roomBedId", nativeQuery = true)
    @Modifying
    void deleteRoomBedById(@Param("roomBedId") Long roomBedId);

    @Query(value = "Select room_bed_code from room_bed WHERE status = 0", nativeQuery = true)
    List<String> findAllActiveRoomBeds();

    @Query(value = "SELECT max(room_bed_id) FROM room_bed", nativeQuery = true)
    Long getMaxId();
}
