package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemParameters;

import java.util.List;

public interface SystemParametersService {

    SystemParameters save(SystemParameters systemParameters);

    SystemParameters update(SystemParameters systemParameters);

    List<SystemParameters> getSystemParametersList();
}
