package com.hospital.management.service;


import com.hospital.management.entities.commom.ServiceCharge;

import java.util.List;

public interface ServiceChargeService {

    ServiceCharge saveServiceCharge(ServiceCharge serviceCharge);

    ServiceCharge updateServiceCharge(ServiceCharge serviceCharge,Integer serviceChargeId);

    List<ServiceCharge> serviceChargeList();
    ServiceCharge findServiceChargeById(Integer serviceChargeId);
    String deleteServiceChargeById(Integer serviceChargeId);


}
