package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ChangeAdmissionDetails;
import com.hospital.management.repositary.ChangeAdmissionDetailsRepo;
import com.hospital.management.service.ChangeAdmissionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeAdmissionDetailsServiceImpl implements ChangeAdmissionDetailsService {

    @Autowired
    ChangeAdmissionDetailsRepo changeAdmissionDetailsRepo;
    @Override
    public void save(ChangeAdmissionDetails changeAdmissionDetails) {
        changeAdmissionDetailsRepo.save(changeAdmissionDetails);
    }

    @Override
    public void update(ChangeAdmissionDetails changeAdmissionDetails) {
        changeAdmissionDetailsRepo.save(changeAdmissionDetails);
    }

    @Override
    public List<ChangeAdmissionDetails> changeAdmissionDetailsList() {
        return changeAdmissionDetailsRepo.findAll();
    }
}
