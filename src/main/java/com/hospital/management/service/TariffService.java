package com.hospital.management.service;


import com.hospital.management.entities.commom.Tariff;

import java.util.List;

public interface TariffService {

    void save(Tariff tariff);

    void update(Tariff tariff);

    List<Tariff> tariffList();
}
