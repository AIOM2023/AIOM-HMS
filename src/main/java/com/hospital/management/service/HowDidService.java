package com.hospital.management.service;

import com.hospital.management.entities.commom.HowDid;

import java.util.List;

public interface HowDidService {

    void save(HowDid howDid);
    void update(HowDid howDid);
    List<HowDid> howDidList();

}
