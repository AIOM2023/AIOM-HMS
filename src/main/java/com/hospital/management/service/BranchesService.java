package com.hospital.management.service;

import com.hospital.management.entities.commom.Branches;

import java.util.List;

public interface BranchesService {

    Branches saveBranches(Branches branches);

    Branches updateBranche(Branches branches,Long branchId);

    List<Branches> branchesList();

    Branches findBranchesById(Long branchId);

    String deleteBranchesById(Long branchId);


}
