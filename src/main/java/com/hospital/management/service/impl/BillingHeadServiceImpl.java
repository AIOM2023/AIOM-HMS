package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.repositary.BillingHeadRepo;
import com.hospital.management.service.BillingHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingHeadServiceImpl implements BillingHeadService {

    @Autowired
    BillingHeadRepo billingHeadRepo;

    @Override
    public void save(BillingHead billingHead) {
        billingHeadRepo.save(billingHead);
    }

    @Override
    public void update(BillingHead billingHead) {
        billingHeadRepo.save(billingHead);
    }

    @Override
    public List<BillingHead> billingHeadList() {
        return billingHeadRepo.findAll();
    }
}
