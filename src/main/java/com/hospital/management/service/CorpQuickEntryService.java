package com.hospital.management.service;

import com.hospital.management.entities.commom.CorpQuickEntry;

import java.util.List;

public interface CorpQuickEntryService {

    void save(CorpQuickEntry corpQuickEntry);

    void update(CorpQuickEntry corpQuickEntry);

    List<CorpQuickEntry> corpQuickEntryList();
}
