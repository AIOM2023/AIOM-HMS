package com.hospital.management.entities.search;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.ServiceRequest;
import com.hospital.management.entities.commom.SystemParameters;
import lombok.Data;

import java.util.List;

@Data
public class ServiceRequestSearchList {

    private MetaData metaData;

    private List<ServiceRequest> data;

}
