package com.hospital.management.repositary;

import com.hospital.management.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, String> {

}
