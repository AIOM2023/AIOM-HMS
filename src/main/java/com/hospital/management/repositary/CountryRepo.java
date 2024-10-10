package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
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
public interface CountryRepo extends JpaRepository<Country, Long > {

    /*@Query(value = "SELECT * FROM master_country WHERE country_code like %?1% OR country_name like %?1% AND status = 0",
            countQuery = "SELECT * FROM master_country WHERE country_code like %?1% OR country_name like %?1% AND status = 0",

           nativeQuery = true)*/
    @Query(value = "SELECT c FROM Country c where c.status=0")
  //  @Query(value = "SELECT c FROM Country c WHERE (c.countryCode LIKE CONCAT('%', ?1, '%') OR c.countryName LIKE CONCAT('%', ?1, '%')) AND c.status = 0")
    Page<Country> findAllCountries(String search, Pageable pageable);

    Optional<Country> findByCountryIdAndStatus(Long countryId, Integer status);

    @Query(value = "SELECT c FROM Country c where c.status=0 and c.countryId = :countryId")
    List<Country> findByCountryId(Long countryId);

    @Query(value = "UPDATE master_country SET status = 1 WHERE country_id = :countryId", nativeQuery = true)
    @Modifying
    void deleteCountryById(@Param("countryId") Long countryId);

    Optional<Country> findByCountryNameAndStatus(String countryName, Integer status);

    @Query(value = "SELECT max(country_id) FROM master_country", nativeQuery = true)
    Long getMaxId();

    @Query(value = "SELECT c FROM Country c where c.status=0 ORDER BY c.countryName ASC")
    List<Country> findAllCountryList();
}
