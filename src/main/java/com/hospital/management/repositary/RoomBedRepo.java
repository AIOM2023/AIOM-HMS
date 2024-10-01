package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RoomBed;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomBedRepo extends JpaRepository<RoomBed,Long> {

    //@Query(value = "SELECT * FROM room_bed WHERE status = 0", nativeQuery = true)
    @Query("SELECT rb FROM RoomBed rb WHERE (rb.roomGroupName LIKE CONCAT('%', ?1, '%') OR rb.roomGroupCode LIKE CONCAT('%', ?1, '%') " +
            "OR rb.roomCode LIKE CONCAT('%', ?1, '%') OR rb.extensionNo LIKE CONCAT('%', ?1, '%') OR rb.roomBlock LIKE CONCAT('%', ?1, '%') " +
            "OR rb.roomLevel LIKE CONCAT('%', ?1, '%') OR rb.roomWing LIKE CONCAT('%', ?1, '%') OR rb.nurseStationCode LIKE CONCAT('%', ?1, '%') " +
            "OR rb.nurseStationName LIKE CONCAT('%', ?1, '%')) AND rb.status = 0")
    Page<RoomBed> findAllRoomBeds(String search, Pageable pageable);


    Optional<RoomBed> findByRoomBedIdAndStatus(Long roomBedId, Integer status);

    @Query(value = "UPDATE room_bed SET status = 1 WHERE room_bed_id = :roomBedId", nativeQuery = true)
    @Modifying
    void deleteRoomBedById(@Param("roomBedId") Long roomBedId);

    @Query(value = "Select room_bed_code from room_bed WHERE status = 0", nativeQuery = true)
    List<String> findAllActiveRoomBeds();

    @Query(value = "SELECT max(room_bed_id) FROM room_bed", nativeQuery = true)
    Long getMaxId();
}
