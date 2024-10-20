package com.hospital.management.repositary;


import com.hospital.management.entities.commom.ConsultCharge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConsultChargeRepo extends JpaRepository<ConsultCharge,Integer> {

    //@Query(value = "SELECT * FROM consult_charge r WHERE r.status = 0", nativeQuery = true)
    @Query("SELECT c FROM ConsultCharge c WHERE (c.consultCode LIKE CONCAT('%', ?1, '%') OR c.tariffId LIKE CONCAT('%', ?1, '%') " +
            "OR c.consultantID LIKE CONCAT('%', ?1, '%') OR c.billingHeadId LIKE CONCAT('%', ?1, '%')) AND c.status = 0")
    Page<ConsultCharge> findAllConsultCharges(String search, Pageable pageable);

    Optional<ConsultCharge> findByConsultIdAndStatus(Long consultId, Integer status);

    @Query(value = "UPDATE consult_charge SET status = 1 WHERE consult_id = :consultId", nativeQuery = true)
    @Modifying
    void deleteConsultChargeById(@Param("consultId") Long consultId);

    @Query(value = "SELECT max(consult_id) FROM consult_charge", nativeQuery = true)
    Long getMaxId();
}
