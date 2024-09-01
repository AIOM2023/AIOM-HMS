package com.hospital.management.service;

import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.entities.response.BillingHeadSearchResult;

import java.util.List;

public interface BillingHeadService {

    BillingHeadSearchResult getAllBillingHeads(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    BillingHead findBillingHeadById(Long billingHeadId);

    BillingHead saveBillingHead(BillingHead billingHead);

    BillingHead updateBillingHead(BillingHead billingHead, Long billingHeadId);

    String deleteBillingHeadById(Long billingHeadId);
}
