package com.hospital.management.repositary;

import com.hospital.management.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepo extends JpaRepository<District, Integer> {

    @Query(value = "SELECT * FROM master_district c WHERE c.status = 0", nativeQuery = true)
    List<District> findAllDistricts();

    Optional<District> findByDistrictIdAndStatus(Integer districtId, Integer status);

    @Query(value = "UPDATE master_district SET status = 1 WHERE district_id = :districtId", nativeQuery = true)
    @Modifying
    void deleteDistrictById(@Param("districtId") Integer districtId);
}
