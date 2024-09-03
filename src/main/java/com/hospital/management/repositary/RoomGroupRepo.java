package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RoomGroup;
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
public interface RoomGroupRepo extends JpaRepository<RoomGroup,Long> {

    @Query(value = "SELECT * FROM room_group WHERE room_group_code like %?1% OR room_group_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<RoomGroup> findAllRoomGroups(String search, Pageable pageable);

    Optional<RoomGroup> findByRoomGroupIdAndStatus(Long roomGroupId, Integer status);

    @Query(value = "UPDATE room_group SET status = 1 WHERE room_group_id = :roomGroupId", nativeQuery = true)
    @Modifying
    void deleteRoomGroupById(@Param("roomGroupId") Long roomGroupId);

    @Query(value = "Select room_group_code from room_group WHERE status = 0", nativeQuery = true)
    List<String> findAllActiveRoomGroupCodes();

    @Query(value = "SELECT max(room_group_id) FROM room_group", nativeQuery = true)
    Long getMaxId();
}
