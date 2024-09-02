package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemParameters;

import java.util.List;

public interface SystemParametersService {

    SystemParameters save(SystemParameters systemParameters);

    SystemParameters update(SystemParameters systemParameters);

    SystemParameters delete(Integer paramId);

    List<SystemParameters> getSystemParametersList();

    List<SystemParameters> getSystemParametersListByMainId(Integer paramsMainId);

    List<SystemParameters> getSystemParametersListById(Integer paramId);


}
