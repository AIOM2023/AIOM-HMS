package com.hospital.management.service;

import com.hospital.management.entities.commom.RegistrationFees;

import java.util.List;

public interface RegistrationFeesService {

    void save(RegistrationFees registrationFees);
    void update(RegistrationFees registrationFees);
    List<RegistrationFees> insuranceCompList();


}
