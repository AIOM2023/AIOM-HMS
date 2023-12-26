package com.hospital.management.service;


import com.hospital.management.entities.commom.ServiceCharge;

import java.util.List;

public interface ServiceChargeService {

    void save(ServiceCharge serviceCharge);

    void update(ServiceCharge serviceCharge);

    List<ServiceCharge> serviceChargeList();

}
