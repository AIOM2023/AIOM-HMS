package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemParamsRepo extends JpaRepository<SystemParameters, Integer> {
}
