package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemParametersMain;

import java.util.List;

public interface SystemParametersMainService {

    void save(SystemParametersMain systemParameters);

    void update(SystemParametersMain systemParameters);

    List<SystemParametersMain> getSystemParametersMainList();
}
