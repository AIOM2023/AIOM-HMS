package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SystemParamsRepo extends JpaRepository<SystemParameters, Integer> {

    @Query(value = "SELECT * FROM common_system_parameters c where c.params_main_id = :paramsMainId", nativeQuery = true)
    Optional<List<SystemParameters>> findAllByparamsMainId(Integer paramsMainId);

}
