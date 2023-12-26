package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepo extends JpaRepository<ServiceRequest,Integer> {
}
