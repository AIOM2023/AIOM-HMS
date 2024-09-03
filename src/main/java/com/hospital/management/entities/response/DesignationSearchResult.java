package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class DesignationSearchResult {
    private MetaData metaData;
    private List<Designation> data;
}
