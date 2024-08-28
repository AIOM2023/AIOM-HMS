package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long > {

    @Query(value = "SELECT * FROM master_country WHERE country_code like %?1% OR country_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<Country> findAllCountries(String search, Pageable pageable);

    Optional<Country> findByCountryIdAndStatus(Long countryId, Integer status);

    @Query(value = "UPDATE master_country SET status = 1 WHERE country_id = :countryId", nativeQuery = true)
    @Modifying
    void deleteCountryById(@Param("countryId") Long countryId);

    @Query(value = "SELECT max(country_id) FROM master_country", nativeQuery = true)
    Long getMaxId();
}
