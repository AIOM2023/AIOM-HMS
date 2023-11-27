package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemParameters;

import java.util.List;

public interface SystemParametersService {

    void save(SystemParameters systemParameters);

    void update(SystemParameters systemParameters);

    List<SystemParameters> getSystemParametersList();
}
