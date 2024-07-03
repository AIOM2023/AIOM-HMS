package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country, Integer > {

    @Query(value = "SELECT * FROM master_country c WHERE c.status = 0", nativeQuery = true)
    List<Country> findAllCountries();

    @Query(value = "UPDATE master_country SET status = 1 WHERE country_id = :countryId", nativeQuery = true)
    @Modifying
    void deleteCountryById(@Param("countryId") Integer countryId);
}
