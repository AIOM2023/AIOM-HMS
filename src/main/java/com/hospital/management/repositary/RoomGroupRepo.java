package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RoomGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomGroupRepo extends JpaRepository<RoomGroup,Long> {

    @Query(value = "SELECT * FROM room_group rg WHERE rg.status = 0", nativeQuery = true)
    List<RoomGroup> findAllRoomGroups();

    Optional<RoomGroup> findByRoomGroupIdAndStatus(Long roomGroupId, Integer status);

    @Query(value = "UPDATE room_group SET status = 1 WHERE room_group_id = :roomGroupId", nativeQuery = true)
    @Modifying
    void deleteRoomGroupById(@Param("roomGroupId") Long roomGroupId);

    @Query(value = "Select room_group_code from room_group WHERE status = 0", nativeQuery = true)
    List<String> findAllActiveRoomGroupCodes();

    @Query(value = "SELECT max(room_group_id) FROM room_group", nativeQuery = true)
    Long getMaxId();
}
