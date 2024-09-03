package com.hospital.management.entities.response;

import com.hospital.management.entities.City;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class CitySearchResult {
    private MetaData metaData;
    private List<City> data;
}
