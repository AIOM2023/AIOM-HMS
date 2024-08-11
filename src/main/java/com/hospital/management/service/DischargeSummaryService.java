package com.hospital.management.service;

import com.hospital.management.entities.commom.DischargeSummary;

import java.util.List;

public interface DischargeSummaryService {

    DischargeSummary saveDischargeSummary(DischargeSummary dischargeSummary);

    DischargeSummary updateDischargeSummary(DischargeSummary dischargeSummary,Long dischargeId);

    List<DischargeSummary> dischargeSummaryList();

    DischargeSummary findDischargeSummaryById(Long dischargeId);

    String deleteDischargeSummaryById(Long dischargeId);
}
