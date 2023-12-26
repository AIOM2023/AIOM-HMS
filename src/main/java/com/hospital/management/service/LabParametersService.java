package com.hospital.management.service;


import com.hospital.management.entities.commom.LabParameters;

import java.util.List;

public interface LabParametersService {

    void save(LabParameters labParameters);

    void update(LabParameters labParameters);

    List<LabParameters> labParametersList();
}
