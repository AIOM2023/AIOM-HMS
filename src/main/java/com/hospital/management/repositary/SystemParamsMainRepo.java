package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParametersMain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemParamsMainRepo extends JpaRepository<SystemParametersMain, Integer> {

    SystemParametersMain findByParamName(String paramName);

    Optional<SystemParametersMain> findAllByparamsMainId(Integer paramsMainId);
}
