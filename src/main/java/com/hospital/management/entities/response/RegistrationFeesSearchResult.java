package com.hospital.management.entities.response;

import com.hospital.management.entities.admin.Referral;
import com.hospital.management.entities.commom.MetaData;
import com.hospital.management.entities.commom.RegistrationFees;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationFeesSearchResult {
    private MetaData metaData;
    private List<RegistrationFees> data;
}
