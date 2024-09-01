package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.BillingHead;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class BillingHeadSearchResult {
    private MetaData metaData;
    private List<BillingHead> data;
}
