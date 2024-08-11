package com.hospital.management.repositary;

import com.hospital.management.entities.commom.DischargeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DischargeSummaryRepo extends JpaRepository<DischargeSummary, Integer > {

    @Query(value = "SELECT * FROM discharge_summary r WHERE r.status = 0", nativeQuery = true)
    List<DischargeSummary> findAllDischargeSummary();


    Optional<DischargeSummary> findByDischargeIdAndStatus(Long dischargeId, Integer status);

    @Query(value = "UPDATE discharge_summary SET status = 1 WHERE dischrg_id = :dischargeId", nativeQuery = true)
    @Modifying
    void deleteDischargeSummaryById(@Param("dischargeId") Long dischargeId);

    @Query(value = "SELECT max(dischrg_id) FROM discharge_summary", nativeQuery = true)
    Long getMaxId();
}
