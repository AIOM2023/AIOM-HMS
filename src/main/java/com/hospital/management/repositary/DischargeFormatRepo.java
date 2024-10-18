package com.hospital.management.repositary;

import com.hospital.management.entities.commom.DischargeFormat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DischargeFormatRepo extends JpaRepository<DischargeFormat, Long > {

  @Query(value = "SELECT df FROM DischargeFormat df where df.status=0")
  Page<DischargeFormat> findAllDischargeFormat(String search, Pageable pageable);

  @Query(value = "SELECT df FROM DischargeFormat df where df.status=0 and df.discFmtId = :dischargeId")
  List<DischargeFormat> findByDiscFmtId(Long dischargeId);

  @Query(value = "SELECT df FROM DischargeFormat df where df.status=0 ORDER BY df.disFmtName ASC")
  List<DischargeFormat> findAllDischargeFormatList();

  DischargeFormat findByDisFmtName(String disFmtName);

  @Query(value = "SELECT max(df.discFmtId) FROM DischargeFormat df")
  Long getMaxId();

}
