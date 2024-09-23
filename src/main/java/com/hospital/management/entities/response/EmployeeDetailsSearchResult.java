package com.hospital.management.entities.response;

import com.hospital.management.entities.commom.EmployeeDetails;
import com.hospital.management.entities.commom.MetaData;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDetailsSearchResult {
    private MetaData metaData;
    private List<EmployeeDetails> data;
}
