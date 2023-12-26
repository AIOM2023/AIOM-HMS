package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.LabParameters;
import com.hospital.management.repositary.LabParametersRepo;
import com.hospital.management.service.LabParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabParametersServiceImpl implements LabParametersService {

    @Autowired
    LabParametersRepo labParametersRepo;
    @Override
    public void save(LabParameters labParameters) {
        labParametersRepo.save(labParameters);
    }

    @Override
    public void update(LabParameters labParameters) {
        labParametersRepo.save(labParameters);
    }

    @Override
    public List<LabParameters> labParametersList() {
        return labParametersRepo.findAll();
    }
}
