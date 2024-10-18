package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.ConsultCharge;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class ConsultChargesSearchResult {
    private MetaData metaData;
    private List<ConsultCharge> data;
}
