package com.hospital.management.repositary;

import com.hospital.management.entities.commom.DischargeFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DischargeFormatRepo extends JpaRepository<DischargeFormat, Integer > {

    @Query(value = "SELECT * FROM dischrg_fmt r WHERE r.status = 0", nativeQuery = true)
    List<DischargeFormat> findAllDischargeFormat();


    Optional<DischargeFormat> findByDiscFmtIdAndStatus(Long dischargeId, Integer status);

    @Query(value = "UPDATE dischrg_fmt SET status = 1 WHERE disc_fmt_id = :discFmtId", nativeQuery = true)
    @Modifying
    void deleteDischargeFormatById(@Param("discFmtId") Long dischargeId);

    @Query(value = "SELECT max(disc_fmt_id) FROM dischrg_fmt", nativeQuery = true)
    Long getMaxId();
}
