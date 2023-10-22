package com.hospital.management.repositary;

import com.hospital.management.entities.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepo extends JpaRepository<CityModel, String> {

}
