package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParametersMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemParamsMainRepo extends JpaRepository<SystemParametersMain, Long> {

    SystemParametersMain findByParamName(String paramName);

    SystemParametersMain findByParamsMainId(Long paramsMainId);

    @Query("SELECT s FROM SystemParametersMain s WHERE s.paramsMainId = ?1")
    Optional<SystemParametersMain> findAllByParamsMainId(Long paramsMainId);

    @Query("SELECT s FROM SystemParametersMain s ORDER BY s.paramName ASC")
    List<SystemParametersMain> findAllSysMainList();
}
