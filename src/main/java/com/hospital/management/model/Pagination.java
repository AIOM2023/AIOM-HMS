package com.hospital.management.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Pagination {

    private int totalDocuments;

    private int totalPages;

    private int currentPage;

    private int documentsPerPage;

}
