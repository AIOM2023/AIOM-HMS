package com.hospital.management.service;

import com.hospital.management.entities.commom.HowDid;
import com.hospital.management.entities.response.HowDidSearchResult;

public interface HowDidService {

    HowDid saveHowDid(HowDid howDid);
    HowDid updateHowDid(HowDid howDid,Long howDidId);
    HowDidSearchResult howDidList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);
    HowDid findHowDidByHowDidId(Long howDidId);
    String deleteHowDidByHowDidId(Long regFeesId);



}
