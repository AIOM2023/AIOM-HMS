package com.hospital.management.service;

import com.hospital.management.entities.admin.Referral;

import java.util.List;

public interface ReferralService {

    Referral saveReferral(Referral referral);

    Referral updateReferral(Referral referral, Long referralId);

    List<Referral> getAllReferrals();

    Referral findReferralById(Long referralId);

    String deleteReferralById(Long referralId);
}
