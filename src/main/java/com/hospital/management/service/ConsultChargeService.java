package com.hospital.management.service;


import com.hospital.management.entities.commom.ConsultCharge;

import java.util.List;

public interface ConsultChargeService {

    void save(ConsultCharge consultCharge);

    void update(ConsultCharge consultCharge);

    List<ConsultCharge> consultChargeList();
}
