package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long > {

    @Query(value = "SELECT * FROM master_country c WHERE c.status = 0", nativeQuery = true)
    List<Country> findAllCountries();

    Optional<Country> findByCountryIdAndStatus(Long countryId, Integer status);

    @Query(value = "UPDATE master_country SET status = 1 WHERE country_id = :countryId", nativeQuery = true)
    @Modifying
    void deleteCountryById(@Param("countryId") Long countryId);

    @Query(value = "SELECT max(country_id) FROM master_country", nativeQuery = true)
    Long getMaxId();
}
