package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ParameterValues;
import com.hospital.management.repositary.ParameterValuesRepo;
import com.hospital.management.service.ParameterValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterValuesServiceImpl implements ParameterValuesService {

    @Autowired
    ParameterValuesRepo parameterValuesRepo;
    @Override
    public void save(ParameterValues parameterValues) {
        parameterValuesRepo.save(parameterValues);
    }

    @Override
    public void update(ParameterValues parameterValues) {
        parameterValuesRepo.save(parameterValues);
    }

    @Override
    public List<ParameterValues> parameterValuesList() {
        return parameterValuesRepo.findAll();
    }
}
