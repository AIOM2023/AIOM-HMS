package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.Branches;
import com.hospital.management.repositary.BranchesRepo;
import com.hospital.management.service.BranchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchesServiceImpl implements BranchesService {

    @Autowired
    BranchesRepo branchesRepo;

    @Override
    public void save(Branches branches) {
        branchesRepo.save(branches);
    }

    @Override
    public void update(Branches branches) {
        branchesRepo.save(branches);
    }

    @Override
    public List<Branches> branchesList() {
        return branchesRepo.findAll();
    }
}
