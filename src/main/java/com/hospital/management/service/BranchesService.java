package com.hospital.management.service;

import com.hospital.management.entities.commom.Branches;

import java.util.List;

public interface BranchesService {

    void save(Branches branches);

    void update(Branches branches);

    List<Branches> branchesList();
}
