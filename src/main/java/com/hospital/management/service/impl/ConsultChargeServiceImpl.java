package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.repositary.ConsultChargeRepo;
import com.hospital.management.service.ConsultChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultChargeServiceImpl implements ConsultChargeService {
    @Autowired
    ConsultChargeRepo consultChargeRepo;
    @Override
    public void save(ConsultCharge consultCharge) {
        consultChargeRepo.save(consultCharge);
    }

    @Override
    public void update(ConsultCharge consultCharge) {
        consultChargeRepo.save(consultCharge);
    }

    @Override
    public List<ConsultCharge> consultChargeList() {
        return  consultChargeRepo.findAll();
    }
}
