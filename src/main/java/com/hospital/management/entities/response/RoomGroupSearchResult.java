package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.RoomGroup;
import lombok.Data;

import java.util.List;

@Data
public class RoomGroupSearchResult {
    private MetaData metaData;
    private List<RoomGroup> data;
}
