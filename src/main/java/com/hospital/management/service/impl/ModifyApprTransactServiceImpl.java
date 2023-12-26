package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.ModifyApprTransact;
import com.hospital.management.repositary.ModifyApprTransactRepo;
import com.hospital.management.service.ModifyApprTransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModifyApprTransactServiceImpl implements ModifyApprTransactService {

    @Autowired
    ModifyApprTransactRepo modifyApprTransactRepo;

    @Override
    public void save(ModifyApprTransact modifyApprTransact) {
        modifyApprTransactRepo.save(modifyApprTransact);
    }

    @Override
    public void update(ModifyApprTransact modifyApprTransact) {
            modifyApprTransactRepo.save(modifyApprTransact);
    }

    @Override
    public List<ModifyApprTransact> modifyApprTransactList() {
        return modifyApprTransactRepo.findAll();
    }
}
