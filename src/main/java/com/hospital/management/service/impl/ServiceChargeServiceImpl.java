package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ServiceCharge;
import com.hospital.management.repositary.ServiceChargeRepo;
import com.hospital.management.service.ServiceChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceChargeServiceImpl implements ServiceChargeService {

    @Autowired
    ServiceChargeRepo serviceChargeRepo;

    @Override
    public void save(ServiceCharge serviceCharge) {
        serviceChargeRepo.save(serviceCharge);
    }

    @Override
    public void update(ServiceCharge serviceCharge) {
        serviceChargeRepo.save(serviceCharge);
    }

    @Override
    public List<ServiceCharge> serviceChargeList() {
        return serviceChargeRepo.findAll();
    }
}
