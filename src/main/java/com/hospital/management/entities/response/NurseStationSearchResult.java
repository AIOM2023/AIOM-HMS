package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.NurseStation;
import lombok.Data;

import java.util.List;

@Data
public class NurseStationSearchResult {
    private MetaData metaData;
    private List<NurseStation> data;
}
