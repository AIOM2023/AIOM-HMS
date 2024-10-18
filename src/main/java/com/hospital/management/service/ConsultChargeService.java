package com.hospital.management.service;


import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.entities.response.ConsultChargesSearchResult;

public interface ConsultChargeService {

    ConsultCharge saveConsultCharge(ConsultCharge consultCharge);

    ConsultCharge updateConsultCharge(ConsultCharge consultCharge,Long consultId);

    ConsultChargesSearchResult getAllConsultCharges(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    ConsultCharge findConsultChargeById(Long consultId);

    String deleteConsultChargeById(Long consultId);
}
