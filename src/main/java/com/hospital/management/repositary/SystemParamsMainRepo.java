package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParametersMain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemParamsMainRepo extends JpaRepository<SystemParametersMain, Integer> {

    SystemParametersMain findByParamName(String paramName);
}
