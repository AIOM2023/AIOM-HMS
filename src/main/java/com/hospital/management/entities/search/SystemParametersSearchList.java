package com.hospital.management.entities.search;

import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.SystemParameters;
import lombok.Data;

import java.util.List;

@Data
public class SystemParametersSearchList {
    private MetaData metaData;
    private List<SystemParameters> data;
}
