package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.commom.ServiceCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceChargeRepo extends JpaRepository<ServiceCharge,Integer> {
    @Query(value = "SELECT * FROM service_charge t WHERE t.status = 0", nativeQuery = true)
    List<ServiceCharge> findAllServiceCharge();


    Optional<ServiceCharge> findByServiceChargeIdAndStatus(Integer serviceChargeId, Integer status);

    @Query(value = "UPDATE service_charge SET status = 1 WHERE service_charge_id = :serviceChargeId", nativeQuery = true)
    @Modifying
    void deleteServiceChargeById(@Param("serviceChargeId") Integer serviceChargeId);
}
