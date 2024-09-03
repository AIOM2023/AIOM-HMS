package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.ServiceGroup;
import lombok.Data;

import java.util.List;

@Data
public class ServiceGroupSearchResult {
    private MetaData metaData;
    private List<ServiceGroup> data;
}
