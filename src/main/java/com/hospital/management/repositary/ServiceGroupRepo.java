package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ServiceGroupRepo extends JpaRepository<ServiceGroup, Long> {

    @Query(value = "SELECT * FROM service_group WHERE service_group_code like %?1% OR service_group_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<ServiceGroup> findAllServiceGroups(String search, Pageable pageable);

    Optional<ServiceGroup> findByServiceGroupIdAndStatus(Long serviceGroupId, Integer status);

    @Query(value = "UPDATE service_group SET status = 1 WHERE service_group_id = :serviceGroupId", nativeQuery = true)
    @Modifying
    void deleteServiceGroupById(@Param("serviceGroupId") Long serviceGroupId);

    @Query(value = "SELECT max(service_group_id) FROM service_group", nativeQuery = true)
    Long getMaxId();
}
