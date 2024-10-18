package com.hospital.management.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hospital.management.entities.commom.SystemParametersMain;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.repositary.SystemParamsMainRepo;
import com.hospital.management.service.SystemParametersMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SystemParametersMainServiceImpl implements SystemParametersMainService {

    private static final Logger logger = LoggerFactory.getLogger(SystemParametersMainServiceImpl.class);

    @Autowired
    SystemParamsMainRepo systemParamsMainRepo;

    @Override
    public SystemParametersMain save(SystemParametersMain systemParametersMain) {
        try {
            SystemParametersMain systemParametersMainExisting = systemParamsMainRepo.findByParamName(systemParametersMain.getParamName());
            if (systemParametersMainExisting != null) {
                throw new DuplicateEntryException("A system parameter with the name '" + systemParametersMainExisting.getParamName() + "' already exists.");
            }
            return  systemParamsMainRepo.save(systemParametersMain);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntryException("System Parameter with the same name already exists", ex);
        }

    }

    @Override
    public SystemParametersMain update(SystemParametersMain systemParametersMain) {
        try {
            SystemParametersMain systemParametersMainExisting = systemParamsMainRepo.findByParamsMainId(systemParametersMain.getParamsMainId());
            if (systemParametersMainExisting != null && !(systemParametersMainExisting.getParamsMainId().equals(systemParametersMain.getParamsMainId()))){
                if (systemParametersMainExisting.getParamName().equals(systemParametersMain.getParamName())) {
                    throw new DuplicateEntryException("A system parameter with the name '" + systemParametersMainExisting.getParamName() + "' already exists.");
                }
            }
            return systemParamsMainRepo.save(systemParametersMain);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateEntryException("System Parameter with the same name already exists", ex);
        }
    }

    @Override
    public List<SystemParametersMain> getSystemParametersMainList() {
        logger.info("Fetching System parameters Main Search List");
        return systemParamsMainRepo.findAllSysMainList();
    }

    @Override
    public SystemParametersMain getSystemParametersMainListById(Long paramsMainId) {
        Optional<SystemParametersMain> systemParametersMain = systemParamsMainRepo.findAllByParamsMainId(paramsMainId);
        return  systemParametersMain.orElseThrow(() ->
                new ResourceNotFoundException(String.format("sysParamsMainId not found with the given Id: %s", paramsMainId)));
    }

}
