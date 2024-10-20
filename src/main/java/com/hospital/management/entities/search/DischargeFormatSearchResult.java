package com.hospital.management.entities.search;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class DischargeFormatSearchResult {
    private MetaData metaData;
    private List<DischargeFormat> data;
}
