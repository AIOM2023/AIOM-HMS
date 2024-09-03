package com.hospital.management.repositary;

import com.hospital.management.entities.commom.DischargeFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DischargeFormatRepo extends JpaRepository<DischargeFormat, Integer > {


  @Query(value = "SELECT * FROM dischrg_fmt WHERE dis_fmt_code like %?1% OR dis_fmt_name like %?1% " +
          "AND status = 0 ORDER BY ?#{#pageable}",
          nativeQuery = true)
  Page<DischargeFormat> findAllDischargeFormat(String search, Pageable pageable);



    Optional<DischargeFormat> findByDiscFmtIdAndStatus(Long dischargeId, Integer status);

    @Query(value = "UPDATE dischrg_fmt SET status = 1 WHERE disc_fmt_id = :discFmtId", nativeQuery = true)
    @Modifying
    void deleteDischargeFormatById(@Param("discFmtId") Long dischargeId);

    @Query(value = "SELECT max(disc_fmt_id) FROM dischrg_fmt", nativeQuery = true)
    Long getMaxId();
}
