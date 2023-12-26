package com.hospital.management.service;

import com.hospital.management.entities.commom.BillingHead;

import java.util.List;

public interface BillingHeadService {

    void save(BillingHead billingHead);

    void update(BillingHead billingHead);

    List<BillingHead> billingHeadList();
}
