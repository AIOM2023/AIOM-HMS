package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.HowDid;
import com.hospital.management.repositary.HowDidRepo;
import com.hospital.management.service.HowDidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HowDidServiceImpl implements HowDidService {

    @Autowired
    HowDidRepo howDidRepo;
    @Override
    public void save(HowDid howDid) {
        howDidRepo.save(howDid);
    }

    @Override
    public void update(HowDid howDid) {
        howDidRepo.save(howDid);
    }

    @Override
    public List<HowDid> howDidList() {
        return howDidRepo.findAll();
    }
}
