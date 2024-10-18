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
   // @Query(value = "SELECT c FROM City c WHERE (c.cityCode LIKE CONCAT('%', ?1, '%') OR c.cityName LIKE CONCAT('%', ?1, '%')) AND status = 0 ORDER BY ?#{#pageable}")
   @Query(value = "SELECT c FROM City c where c.status=0")
    Page<City> findAllCities(String search, Pageable pageable);
    /*  @Query("SELECT c FROM Country c WHERE (c.countryCode LIKE CONCAT('%', ?1, '%') OR c.countryName LIKE CONCAT('%', ?1, '%')) AND c.status = 0")
    Page<Country> findAllCountries(String search, Pageable pageable);*/

    Optional<City> findByCityIdAndStatus(Long cityId, Integer status);
    City findByCityName(String cityName);
    @Query(value = "UPDATE master_city SET status = 1 WHERE city_id = :cityId", nativeQuery = true)
    @Modifying
    void deleteCityById(@Param("cityId") Long cityId);

    @Query(value = "SELECT max(city_id) FROM master_city", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT c FROM City c WHERE c.status=0 and c.district.districtId IN :districtId Order by c.district.districtName ASC")
    Optional<List<City>> findAllCityNamesAndDistrictId(List<Long> districtId);


   @Query(value = "SELECT d FROM City d where d.status=0 and d.cityId = :cityId")
   List<City> findByCityIdAndStatus(Long cityId);

 @Query(value = "SELECT d FROM City d where d.status=0 ORDER BY d.cityName ASC")
 List<City> findAllCityList();


}
