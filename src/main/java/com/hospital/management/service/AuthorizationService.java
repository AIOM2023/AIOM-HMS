package com.hospital.management.service;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.Authorization;
import com.hospital.management.model.common.AddAuthorizationModel;

import java.util.List;

public interface AuthorizationService {
    
    void save(Authorization authorization);

    void update(Authorization authorization);

    List<Authorization> getAuthorizationList();
}
