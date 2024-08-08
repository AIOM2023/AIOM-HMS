package com.hospital.management.service.impl;


import com.hospital.management.entities.commom.SystemParametersMain;
import com.hospital.management.repositary.SystemParamsMainRepo;
import com.hospital.management.service.SystemParametersMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemParametersMainServiceImpl implements SystemParametersMainService {

    @Autowired
    SystemParamsMainRepo systemParamsMainRepo;

    @Override
    public void save(SystemParametersMain systemParametersMain) { systemParamsMainRepo.save(systemParametersMain); }

    @Override
    public void update(SystemParametersMain systemParametersMain) { systemParamsMainRepo.save(systemParametersMain); }

    @Override
    public List<SystemParametersMain> getSystemParametersMainList() { return systemParamsMainRepo.findAll(); }

}
