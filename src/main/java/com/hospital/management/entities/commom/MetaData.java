package com.hospital.management.entities.commom;

import lombok.Data;

@Data
public class MetaData {
    private Long totalRecords;
    private Long totalPages;
    private int currentPage;
    private int recordsPerPage;
}
