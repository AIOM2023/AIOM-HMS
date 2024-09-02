package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.repositary.SystemParamsRepo;
import com.hospital.management.service.SystemParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SystemParamServiceImpl implements SystemParametersService {

    @Autowired
    SystemParamsRepo systemParamsRepo;

    @Override
    public SystemParameters save(SystemParameters systemParameters) {
        return systemParamsRepo.save(systemParameters);
    }

    @Override
    public SystemParameters update(SystemParameters systemParameters) { return systemParamsRepo.save(systemParameters);}

    @Override
    public SystemParameters delete(Integer paramId) {
        SystemParameters systemParameters=systemParamsRepo.findById(paramId)
                .orElseThrow(() -> new RuntimeException("System Params Id Not Found"));
        systemParameters.setStatus(1);
        systemParamsRepo.save(systemParameters);
        return systemParameters;
    }

    @Override
    public List<SystemParameters> getSystemParametersList() {
        return systemParamsRepo.findAllSystemParams();
    }

    @Override
    public List<SystemParameters> getSystemParametersListByMainId(Integer paramsMainId) {
        Optional<List<SystemParameters>> systemParametersbyMainId = systemParamsRepo.findAllByparamsMainId(paramsMainId);
        return  systemParametersbyMainId.orElseThrow(() ->
                new ResourceNotFoundException(String.format("sysParamsMainId not found with the given Id: %s", paramsMainId)));
    }

    @Override
    public List<SystemParameters> getSystemParametersListById(Integer paramId) {
        Optional<List<SystemParameters>> systemParametersbyMainId = systemParamsRepo.findAllByparamId(paramId);
        return  systemParametersbyMainId.orElseThrow(() ->
                new ResourceNotFoundException(String.format("sysParamsMainId not found with the given Id: %s", paramId)));
    }
}
