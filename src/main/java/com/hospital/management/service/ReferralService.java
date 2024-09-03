package com.hospital.management.service;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.entities.response.ReferralSearchResult;

import java.util.List;

public interface ReferralService {

    Referral saveReferral(Referral referral);

    Referral updateReferral(Referral referral, Long referralId);

    ReferralSearchResult getAllReferrals(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    Referral findReferralById(Long referralId);

    String deleteReferralById(Long referralId);
}
