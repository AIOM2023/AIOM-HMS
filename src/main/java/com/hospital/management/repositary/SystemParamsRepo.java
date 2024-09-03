package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SystemParamsRepo extends JpaRepository<SystemParameters, Integer> {

    @Query(value = "SELECT * FROM common_system_parameters cs where cs.status=0", nativeQuery = true)
    List<SystemParameters> findAllSystemParams();

    @Query(value = "SELECT * FROM common_system_parameters c where c.params_main_id = :paramsMainId and c.status=0", nativeQuery = true)
    Optional<List<SystemParameters>> findAllByparamsMainId(Integer paramsMainId);

    @Query(value = "SELECT * FROM common_system_parameters c where c.param_id = :paramId and c.status=0", nativeQuery = true)
    Optional<List<SystemParameters>> findAllByparamId(Integer paramId);

}
