package com.hospital.management.repositary;

import com.hospital.management.entities.commom.ServiceCharge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceChargeRepo extends JpaRepository<ServiceCharge,Integer> {
}
