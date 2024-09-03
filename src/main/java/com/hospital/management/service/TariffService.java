package com.hospital.management.service;


import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.entities.response.TariffSearchResult;

import java.util.List;

public interface TariffService {

    Tariff save(Tariff tariff);

    Tariff update(Tariff tariff, Long tariffId);

    TariffSearchResult tariffList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    Tariff findTariffById(Long tariffId);

    String deleteTariffById(Long tariffId);
}
