package com.hospital.management.service;


import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.entities.commom.DischargeSummary;

import java.util.List;

public interface ConsultChargeService {

    ConsultCharge saveConsultCharge(ConsultCharge consultCharge);

    ConsultCharge updateConsultCharge(ConsultCharge consultCharge,Long consultId);

    List<ConsultCharge> consultChargeList();

    ConsultCharge findConsultChargeById(Long consultId);

    String deleteConsultChargeById(Long consultId);
}
