package com.hospital.management.repositary;

import com.hospital.management.entities.District;
import com.hospital.management.entities.State;
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
   // @Query(value = "SELECT d FROM District d WHERE (d.districtCode LIKE CONCAT('%', ?1, '%') OR d.districtName LIKE CONCAT('%', ?1, '%')) AND d.status = 0")
    @Query(value = "SELECT d FROM District d where d.status=0")
    Page<District> findAllDistricts(String search, Pageable pageable);

    Optional<District> findByDistrictIdAndStatus(Long districtId, Integer status);
    District findByDistrictName(String districtName);
    @Query(value = "UPDATE master_district SET status = 1 WHERE district_id = :districtId", nativeQuery = true)
    @Modifying
    void deleteDistrictById(@Param("districtId") Long districtId);

    @Query(value = "SELECT max(district_id) FROM master_district", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT c FROM District c WHERE c.status=0 and c.state.stateId IN :stateId Order by districtName ASC")
    Optional<List<District>> findAllDistrictNamesAndDistrictId(List<Long> stateId);

    @Query(value = "SELECT d FROM District d where d.status=0 and d.districtId = :districtId")
    List<District> findByDistrictId(Long districtId);

    @Query(value = "SELECT d FROM District d where d.status=0 ORDER BY d.districtName ASC")
    List<District> findAllDistrictList();


}
