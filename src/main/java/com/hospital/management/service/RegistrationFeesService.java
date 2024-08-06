package com.hospital.management.service;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.entities.commom.Tariff;

import java.util.List;

public interface RegistrationFeesService {

    RegistrationFees saveregistrationFees(RegistrationFees registrationFees);
    RegistrationFees updateregistrationFees(RegistrationFees registrationFees,Integer regFeesId);
   // List<RegistrationFees> insuranceCompList();

    List<RegistrationFees> registrationFeesList();

    RegistrationFees findRegistrationFeesId(Integer regFeesId);

    String deleteRegistrationFeesById(Integer regFeesId);


}
