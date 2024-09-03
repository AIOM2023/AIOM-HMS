package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.HowDid;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class HowDidSearchResult {
    private MetaData metaData;
    private List<HowDid> data;
}
