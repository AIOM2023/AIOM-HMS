package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.repositary.TariffRepo;
import com.hospital.management.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {

    @Autowired
    TariffRepo tariffRepo;

    @Override
    public void save(Tariff tariff) {
        tariffRepo.save(tariff);
    }

    @Override
    public void update(Tariff tariff) {
        tariffRepo.save(tariff);
    }

    @Override
    public List<Tariff> tariffList() {
        return tariffRepo.findAll();
    }
}
