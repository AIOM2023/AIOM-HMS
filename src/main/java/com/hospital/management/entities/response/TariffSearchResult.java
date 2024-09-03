package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.Tariff;
import lombok.Data;

import java.util.List;

@Data
public class TariffSearchResult {
    private MetaData metaData;
    private List<Tariff> data;
}
