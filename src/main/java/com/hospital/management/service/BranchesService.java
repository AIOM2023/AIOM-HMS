package com.hospital.management.service;

import com.hospital.management.entities.commom.Branches;
import com.hospital.management.entities.response.BranchesSearchResult;

import java.util.List;

public interface BranchesService {

    Branches saveBranches(Branches branches);

    Branches updateBranche(Branches branches,Long branchId);

    BranchesSearchResult branchesList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    Branches findBranchesById(Long branchId);

    String deleteBranchesById(Long branchId);


}
