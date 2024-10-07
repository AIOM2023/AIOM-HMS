package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServiceRequestRepo extends JpaRepository<ServiceRequest,Long> {

    @Query(value = "SELECT sr FROM ServiceRequest sr where sr.status=0")
    Page<ServiceRequest> findAllServiceRequests(String search, Pageable pageable);

    ServiceRequest findByServiceRequestName(String serviceRequestName);

    @Query(value = "SELECT sr FROM ServiceRequest sr where sr.status=0 and sr.serviceRequestId = :serviceRequestId")
    List<ServiceRequest> findServiceRequestById(Long serviceRequestId);

    @Query(value = "SELECT max(sr.serviceRequestId) FROM ServiceRequest sr")
    Long getMaxId();
}
