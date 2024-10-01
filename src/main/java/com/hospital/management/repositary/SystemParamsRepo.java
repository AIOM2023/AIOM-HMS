package com.hospital.management.repositary;

import com.hospital.management.entities.commom.SystemParameters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SystemParamsRepo extends JpaRepository<SystemParameters, Long> {

    @Query(value = "SELECT cs FROM SystemParameters cs where cs.status=0")
    Page<SystemParameters> findAllSystemParams(String search, Pageable pageable);

    @Query(value = "SELECT * FROM common_system_parameters c where c.params_main_id = :paramsMainId and c.status=0", nativeQuery = true)
    Optional<List<SystemParameters>> findAllByParamsMainId(Long paramsMainId);

    @Query(value = "SELECT * FROM common_system_parameters c where c.param_id = :paramId and c.status=0", nativeQuery = true)
    Optional<List<SystemParameters>> findAllByParamId(Long paramId);

    SystemParameters findByCommonValue(String commonValue);

    @Query(value="SELECT cs FROM SystemParameters cs where cs.status=0 and cs.paramId IN :sysParamIds")
    Optional<List<SystemParameters>> findAllByParamsIds(List<Long> sysParamIds);
}
