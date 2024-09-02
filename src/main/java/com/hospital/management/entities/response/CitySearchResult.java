package com.hospital.management.entities.response;

import com.hospital.management.entities.State;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class StateSearchResult {
    private MetaData metaData;
    private List<State> data;
}
