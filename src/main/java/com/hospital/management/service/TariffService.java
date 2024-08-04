package com.hospital.management.service;


import com.hospital.management.entities.commom.Tariff;

import java.util.List;

public interface TariffService {

    Tariff save(Tariff tariff);

    Tariff update(Tariff tariff, Long tariffId);

    List<Tariff> tariffList();

    Tariff findTariffById(Long tariffId);

    String deleteTariffById(Long tariffId);
}
