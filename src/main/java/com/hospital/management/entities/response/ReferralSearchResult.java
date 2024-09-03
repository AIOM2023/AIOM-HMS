package com.hospital.management.entities.response;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class ReferralSearchResult {
    private MetaData metaData;
    private List<Referral> data;
}
