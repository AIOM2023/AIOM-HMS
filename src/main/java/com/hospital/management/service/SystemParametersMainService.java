package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemParametersMain;

import java.util.List;

public interface SystemParametersMainService {

    SystemParametersMain save(SystemParametersMain systemParameters);

    SystemParametersMain update(SystemParametersMain systemParameters);

    List<SystemParametersMain> getSystemParametersMainList();

    SystemParametersMain getSystemParametersMainListById(Long paramsMainId);
}
