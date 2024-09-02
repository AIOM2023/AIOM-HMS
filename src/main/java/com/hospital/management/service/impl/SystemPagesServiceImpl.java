package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.SystemPages;
import com.hospital.management.repositary.SystemPagesRepo;
import com.hospital.management.service.SystemPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPagesServiceImpl implements SystemPagesService {

    @Autowired
    SystemPagesRepo systemPagesRepo;

    @Override
    public SystemPages save(SystemPages systemPages) {
        systemPagesRepo.save(systemPages);
        return systemPages;
    }

    @Override
    public SystemPages update(SystemPages systemPages) {
        systemPagesRepo.save(systemPages);
        return systemPages;
    }

    @Override
    public SystemPages delete(Long systemPagesId) {
        SystemPages systemPages = systemPagesRepo.findById(systemPagesId).
                orElseThrow(() -> new RuntimeException("SystemPageId Not Found"));
        systemPages.setStatus(1);
        systemPagesRepo.save(systemPages);
        return systemPages;
    }

    @Override
    public List<SystemPages> getSystemPagesList() {
        return systemPagesRepo.getSystemPagesList();
    }
}
