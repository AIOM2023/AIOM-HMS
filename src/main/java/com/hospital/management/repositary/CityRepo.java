package com.hospital.management.repositary;

import com.hospital.management.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Long> {
    @Query(value = "SELECT * FROM master_city WHERE CITY_CODE like %?1% OR CITY_NAME like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<City> findAllCities(String search, Pageable pageable);

    Optional<City> findByCityIdAndStatus(Long cityId, Integer status);

    @Query(value = "UPDATE master_city SET status = 1 WHERE city_id = :cityId", nativeQuery = true)
    @Modifying
    void deleteCityById(@Param("cityId") Long cityId);

    @Query(value = "SELECT max(city_id) FROM master_city", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT CITY_NAME FROM master_city WHERE DISTRICT_NAME = :districtName", nativeQuery = true)
    List<String> findAllCityNames(String districtName);
}
