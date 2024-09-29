package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.entities.search.SystemParametersSearchList;

import java.util.List;

public interface SystemParametersService {

    SystemParameters save(SystemParameters systemParameters);

    SystemParameters update(SystemParameters systemParameters);

    SystemParameters delete(Long paramId);

    SystemParametersSearchList getSystemParametersList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<SystemParameters> getSystemParametersListByMainId(Long paramsMainId);

    List<SystemParameters> getSystemParametersListById(Long paramId);

    List<SystemParameters> getSystemParametersWithIds(List<Long> paramIds);

}
