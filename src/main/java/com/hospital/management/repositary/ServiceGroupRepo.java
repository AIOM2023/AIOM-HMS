package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceGroupRepo extends JpaRepository<ServiceGroup, Long> {

    @Query(value = "SELECT * FROM service_group sg WHERE sg.status = 0", nativeQuery = true)
    List<ServiceGroup> findAllServiceGroups();

    Optional<ServiceGroup> findByServiceGroupIdAndStatus(Long serviceGroupId, Integer status);

    @Query(value = "UPDATE service_group SET status = 1 WHERE service_group_id = :serviceGroupId", nativeQuery = true)
    @Modifying
    void deleteServiceGroupById(@Param("serviceGroupId") Long serviceGroupId);

    @Query(value = "SELECT max(service_group_id) FROM service_group", nativeQuery = true)
    Long getMaxId();
}
