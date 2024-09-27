package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.RoomBed;
import lombok.Data;

import java.util.List;

@Data
public class RoomBedSearchResult {
    private MetaData metaData;
    private List<RoomBed> data;
}
