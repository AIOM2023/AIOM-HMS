package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.ServiceRequest;
import lombok.Data;

import java.util.List;

@Data
public class ServiceRequestResult {
    private MetaData metaData;
    private List<ServiceRequest> data;
}
