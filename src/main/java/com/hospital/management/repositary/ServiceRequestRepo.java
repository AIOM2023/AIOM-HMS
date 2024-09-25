package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServiceRequestRepo extends JpaRepository<ServiceRequest,Long> {

    @Query("SELECT c FROM ServiceRequest c WHERE (c.serviceRequestCode LIKE CONCAT('%', ?1, '%') OR c.serviceRequestName LIKE CONCAT('%', ?1, '%')) AND c.status = 0")
    Page<ServiceRequest> findAllServiceRequests(String search, Pageable pageable);

    Optional<ServiceRequest> findByServiceRequestIdAndStatus(Long serviceRequestId, Integer status);

    @Query(value = "UPDATE service_request SET status = 1 WHERE service_request_id = :serviceRequestId", nativeQuery = true)
    @Modifying
    void deleteServiceRequestById(@Param("serviceRequestId") Long serviceRequestId);

    @Query(value = "SELECT max(service_request_id) FROM service_request", nativeQuery = true)
    Long getMaxId();
}
