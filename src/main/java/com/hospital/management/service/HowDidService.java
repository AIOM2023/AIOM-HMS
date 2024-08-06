package com.hospital.management.service;

import com.hospital.management.entities.commom.HowDid;

import java.util.List;

public interface HowDidService {

    HowDid saveHowDid(HowDid howDid);
    HowDid updateHowDid(HowDid howDid,Integer howDidId);
    List<HowDid> howDidList();
    HowDid findHowDidByHowDidId(Integer howDidId);
    String deleteHowDidByHowDidId(Integer regFeesId);



}
