package com.hospital.management.service;



import com.hospital.management.entities.commom.BedDetails;

import java.util.List;

public interface BedDetailsService {

    void save(BedDetails bedDetails);

    void update(BedDetails bedDetails);

    List<BedDetails> bedDetailsList();
}
