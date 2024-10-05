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

    @Query(value = "SELECT c FROM SystemParameters c where c.status=0 and c.systemParametersMain.paramsMainId IN :paramsMainId Order by c.systemParametersMain.paramName ASC")
    Optional<List<SystemParameters>> findAllByParamsMainId(List<Long> paramsMainId);

    @Query(value = "SELECT c FROM SystemParameters c where c.paramId IN :paramIds and c.status=0")
    Optional<List<SystemParameters>> findAllByParamId(Long paramIds);

    SystemParameters findByCommonValue(String commonValue);

    @Query(value="SELECT cs FROM SystemParameters cs where cs.status=0 and cs.paramId IN :sysParamIds")
    Optional<List<SystemParameters>> findAllByParamsIds(List<Long> sysParamIds);

    SystemParameters findByParamIdAndCommonValue(Long paramId,String commonValue);
}
