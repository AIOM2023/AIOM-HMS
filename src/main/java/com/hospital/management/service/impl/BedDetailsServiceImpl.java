package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.BedDetails;
import com.hospital.management.repositary.BedDetailsRepo;
import com.hospital.management.service.BedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedDetailsServiceImpl implements BedDetailsService {

    @Autowired
    BedDetailsRepo bedDetailsRepo;

    @Override
    public void save(BedDetails bedDetails) {
        bedDetailsRepo.save(bedDetails);
    }

    @Override
    public void update(BedDetails bedDetails) {
        bedDetailsRepo.save(bedDetails);
    }

    @Override
    public List<BedDetails> bedDetailsList() {
        return bedDetailsRepo.findAll();
    }

}
