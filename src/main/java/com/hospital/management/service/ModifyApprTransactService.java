package com.hospital.management.service;


import com.hospital.management.entities.commom.ModifyApprTransact;

import java.util.List;

public interface ModifyApprTransactService {

    void save(ModifyApprTransact modifyApprTransact);

    void update(ModifyApprTransact modifyApprTransact);

    List<ModifyApprTransact> modifyApprTransactList();
}
