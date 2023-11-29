package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.DischargeSummary;
import com.hospital.management.repositary.DischargeSummaryRepo;
import com.hospital.management.service.DischargeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DischargeServiceImpl implements DischargeSummaryService {

    @Autowired
    DischargeSummaryRepo dischargeSummaryRepo;

    @Override
    public void save(DischargeSummary dischargeSummary) {
        dischargeSummaryRepo.save(dischargeSummary);
    }

    @Override
    public void update(DischargeSummary dischargeSummary) {
        dischargeSummaryRepo.save(dischargeSummary);
    }

    @Override
    public List<DischargeSummary> dischargeSummaryList() {
        return dischargeSummaryRepo.findAll();
    }
}
