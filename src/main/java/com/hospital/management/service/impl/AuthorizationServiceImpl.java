package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Authorization;
import com.hospital.management.repositary.AuthorizationRepo;
import com.hospital.management.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private AuthorizationRepo authorizationRepo;

    @Override
    public void save(Authorization authorization) {
        authorizationRepo.save(authorization);
    }

    @Override
    public void update(Authorization authorization) {
        authorizationRepo.save(authorization);
    }

    @Override
    public List<Authorization> getAuthorizationList() {
        return authorizationRepo.findAll();
    }


}
