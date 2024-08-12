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
    public SystemParametersMain save(SystemParametersMain systemParametersMain) {
        return  systemParamsMainRepo.save(systemParametersMain);
    }

    @Override
    public SystemParametersMain update(SystemParametersMain systemParametersMain) {
        return systemParamsMainRepo.save(systemParametersMain); }

    @Override
    public List<SystemParametersMain> getSystemParametersMainList() { return systemParamsMainRepo.findAll(); }

}
