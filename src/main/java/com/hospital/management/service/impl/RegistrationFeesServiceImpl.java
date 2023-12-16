package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.repositary.RegistrationFeesRepo;
import com.hospital.management.service.RegistrationFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationFeesServiceImpl implements RegistrationFeesService {

    @Autowired
    RegistrationFeesRepo registrationFeesRepo;

    @Override
    public void save(RegistrationFees registrationFees) {
        registrationFeesRepo.save(registrationFees);
    }

    @Override
    public void update(RegistrationFees registrationFees) {
        registrationFeesRepo.save(registrationFees);
    }

    @Override
    public List<RegistrationFees> insuranceCompList() {
        return registrationFeesRepo.findAll();
    }
}
