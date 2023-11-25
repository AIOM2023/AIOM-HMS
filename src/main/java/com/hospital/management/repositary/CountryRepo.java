package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, String > {
}
