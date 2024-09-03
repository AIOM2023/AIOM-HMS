package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.Specialization;
import lombok.Data;

import java.util.List;

@Data
public class SpecializationSearchResult {
    private MetaData metaData;
    private List<Specialization> data;
}
