package com.hospital.management.entities.response;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class CountrySearchResult {
    private MetaData metaData;
    private List<Country> data;
}
