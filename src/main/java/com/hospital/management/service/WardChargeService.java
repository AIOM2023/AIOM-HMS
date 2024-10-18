package com.hospital.management.service;

import com.hospital.management.entities.commom.WardCharge;

import java.util.List;

public interface WardChargeService {
    List<WardCharge> getAllWards();

    WardCharge findWardById(Long wardId);

    WardCharge saveWard(WardCharge wardCharge);

    WardCharge updateWard(WardCharge wardCharge, Long wardId);

    String deleteWardById(Long wardId);
}
