package com.hospital.management.repositary;

import com.hospital.management.entities.commom.WardCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardChargeRepo extends JpaRepository<WardCharge, Long> {
    @Query("SELECT wc FROM WardCharge wc WHERE wc.status = 0")
    List<WardCharge> findAllWards();

    Optional<WardCharge> findByWardIdAndStatus(Long wardId, Integer status);

    @Query(value = "UPDATE ward_charges SET status = 1 WHERE ward_id = :wardId", nativeQuery = true)
    @Modifying
    void deleteWardById(@Param("wardId") Long wardId);

    @Query(value = "SELECT max(ward_id) FROM ward_charges", nativeQuery = true)
    Long getMaxId();

}
