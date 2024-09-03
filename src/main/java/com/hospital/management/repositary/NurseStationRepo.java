package com.hospital.management.repositary;


import com.hospital.management.entities.commom.NurseStation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NurseStationRepo extends JpaRepository<NurseStation, Integer > {


    @Query(value = "SELECT * FROM nurse_station WHERE nurse_station_code like %?1% OR nurse_station_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<NurseStation> findAllNurseStations(String search, Pageable pageable);

    Optional<NurseStation> findByNurseStationIdAndStatus(Long nurseStationId, Integer status);

    @Query(value = "UPDATE nurse_station SET status = 1 WHERE nurse_station_id = :nurseStationId", nativeQuery = true)
    @Modifying
    void deleteNurseStationId(@Param("nurseStationId") Long nurseStationId);

    @Query(value = "SELECT max(nurse_station_id) FROM nurse_station", nativeQuery = true)
    Long getMaxId();
}
