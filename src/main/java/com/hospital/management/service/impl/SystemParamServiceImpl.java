package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.repositary.SystemParamsRepo;
import com.hospital.management.service.SystemParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SystemParamServiceImpl implements SystemParametersService {

    @Autowired
    SystemParamsRepo systemParamsRepo;

    @Override
    public void save(SystemParameters systemParameters) {
        systemParamsRepo.save(systemParameters);
    }

    @Override
    public void update(SystemParameters systemParameters) { systemParamsRepo.save(systemParameters);}

    @Override
    public List<SystemParameters> getSystemParametersList() {
        return systemParamsRepo.findAll();
    }
}
