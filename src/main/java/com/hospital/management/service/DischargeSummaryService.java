package com.hospital.management.service;

import com.hospital.management.entities.commom.DischargeSummary;

import java.util.List;

public interface DischargeSummaryService {

    void save(DischargeSummary dischargeSummary);

    void update(DischargeSummary dischargeSummary);

    List<DischargeSummary> dischargeSummaryList();
}
