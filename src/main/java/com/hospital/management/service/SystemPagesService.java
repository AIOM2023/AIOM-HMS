package com.hospital.management.service;

import com.hospital.management.entities.commom.SystemPages;


import java.util.List;

public interface SystemPagesService {

    SystemPages save(SystemPages systemPages);

    SystemPages update(SystemPages systemPages);

    SystemPages delete(Long systemPagesId);

    List<SystemPages> getSystemPagesList();
}
