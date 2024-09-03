package com.hospital.management.service;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.entities.response.ReferralSearchResult;
import com.hospital.management.entities.response.RegistrationFeesSearchResult;

import java.util.List;

public interface RegistrationFeesService {

    RegistrationFees saveregistrationFees(RegistrationFees registrationFees);
    RegistrationFees updateregistrationFees(RegistrationFees registrationFees,Long regFeesId);
   // List<RegistrationFees> insuranceCompList();

    RegistrationFeesSearchResult registrationFeesList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    RegistrationFees findRegistrationFeesId(Long regFeesId);

    String deleteRegistrationFeesById(Long regFeesId);


}
