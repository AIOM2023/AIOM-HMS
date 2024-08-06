package com.hospital.management.repositary;


import com.hospital.management.entities.commom.NurseStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NurseStationRepo extends JpaRepository<NurseStation, Integer > {

    @Query(value = "SELECT * FROM nurse_station c WHERE c.status = 0", nativeQuery = true)
    List<NurseStation> findAllNurseStations();

    Optional<NurseStation> findByNurseStationIdAndStatus(Integer nurseStationId, Integer status);

    @Query(value = "UPDATE nurse_station SET status = 1 WHERE nurse_station_id = :nurseStationId", nativeQuery = true)
    @Modifying
    void deleteNurseStationId(@Param("nurseStationId") Integer nurseStationId);
}
