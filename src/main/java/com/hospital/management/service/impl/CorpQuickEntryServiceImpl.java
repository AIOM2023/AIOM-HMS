package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.CorpQuickEntry;
import com.hospital.management.repositary.CorpQuickEntryRepo;
import com.hospital.management.service.CorpQuickEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpQuickEntryServiceImpl implements CorpQuickEntryService {
    @Autowired
    CorpQuickEntryRepo corpQuickEntryRepo;
    @Override
    public void save(CorpQuickEntry corpQuickEntry) {
        corpQuickEntryRepo.save(corpQuickEntry);
    }

    @Override
    public void update(CorpQuickEntry corpQuickEntry) {
        corpQuickEntryRepo.save(corpQuickEntry);
    }

    @Override
    public List<CorpQuickEntry> corpQuickEntryList() {
        return corpQuickEntryRepo.findAll();
    }
}
