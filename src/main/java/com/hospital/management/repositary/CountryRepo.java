package com.hospital.management.repositary;

import com.hospital.management.entities.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<CountryModel, String > {
}
