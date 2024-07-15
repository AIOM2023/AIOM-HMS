package com.hospital.management.repositary;

import com.hospital.management.entities.City;
import com.hospital.management.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepo extends JpaRepository<City, Integer> {
    @Query(value = "SELECT * FROM master_city c WHERE c.status = 0", nativeQuery = true)
    List<City> findAllCities();

    Optional<City> findByCityIdAndStatus(Integer cityId, Integer status);

    @Query(value = "UPDATE master_city SET status = 1 WHERE city_id = :cityId", nativeQuery = true)
    @Modifying
    void deleteCityById(@Param("cityId") Integer cityId);
}
