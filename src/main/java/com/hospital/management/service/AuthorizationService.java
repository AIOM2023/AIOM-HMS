package com.hospital.management.service;

import com.hospital.management.entities.commom.Authorization;

import java.util.List;

public interface AuthorizationService {
    
    void save(Authorization authorization);

    void update(Authorization authorization);

    List<Authorization> getAuthorizationList();
}
