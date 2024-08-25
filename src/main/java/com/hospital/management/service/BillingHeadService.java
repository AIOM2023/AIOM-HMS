package com.hospital.management.service;

import com.hospital.management.entities.commom.BillingHead;

import java.util.List;

public interface BillingHeadService {

    List<BillingHead> getAllBillingHeads();

    BillingHead findBillingHeadById(Long billingHeadId);

    BillingHead saveBillingHead(BillingHead billingHead);

    BillingHead updateBillingHead(BillingHead billingHead, Long billingHeadId);

    String deleteBillingHeadById(Long billingHeadId);
}
