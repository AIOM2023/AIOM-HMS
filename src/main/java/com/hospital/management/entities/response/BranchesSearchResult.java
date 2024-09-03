package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.Branches;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class BranchesSearchResult {
    private MetaData metaData;
    private List<Branches> data;
}
