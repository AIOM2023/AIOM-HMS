package com.hospital.management.entities.response;

import com.hospital.management.entities.District;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class DistrictSearchResult {
    private MetaData metaData;
    private List<District> data;
}
