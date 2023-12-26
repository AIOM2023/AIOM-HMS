package com.hospital.management.service;


import com.hospital.management.entities.commom.ParameterValues;

import java.util.List;

public interface ParameterValuesService {

    void save(ParameterValues parameterValues);

    void update(ParameterValues parameterValues);

    List<ParameterValues> parameterValuesList();
}
