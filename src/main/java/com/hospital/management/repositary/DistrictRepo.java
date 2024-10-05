package com.hospital.management.repositary;

import com.hospital.management.entities.City;
import com.hospital.management.entities.District;
import com.hospital.management.entities.response.DistrictNameId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@EnableJpaRepositories
@Repository
public interface DistrictRepo extends JpaRepository<District, Long> {
    //@Query("SELECT c FROM Country c WHERE (c.countryCode LIKE CONCAT('%', ?1, '%') OR c.countryName LIKE CONCAT('%', ?1, '%')) AND c.status = 0")
    @Query(value = "SELECT d FROM District d WHERE (d.districtCode LIKE CONCAT('%', ?1, '%') OR d.districtName LIKE CONCAT('%', ?1, '%')) AND d.status = 0")
    Page<District> findAllDistricts(String search, Pageable pageable);

    Optional<District> findByDistrictIdAndStatus(Long districtId, Integer status);
    District findByDistrictName(String districtName);
    @Query(value = "UPDATE master_district SET status = 1 WHERE district_id = :districtId", nativeQuery = true)
    @Modifying
    void deleteDistrictById(@Param("districtId") Long districtId);

    @Query(value = "SELECT max(district_id) FROM master_district", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT district_name,district_id FROM master_district WHERE STATE_ID = :stateId", nativeQuery = true)
    List<DistrictNameId> findAllDistrictNamesAndDistrictId(Long stateId);
}
