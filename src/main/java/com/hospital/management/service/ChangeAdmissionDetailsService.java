package com.hospital.management.service;

import com.hospital.management.entities.commom.CaptionDay;
import com.hospital.management.entities.commom.ChangeAdmissionDetails;

import java.util.List;

public interface ChangeAdmissionDetailsService {

    void save(ChangeAdmissionDetails changeAdmissionDetails);

    void update(ChangeAdmissionDetails changeAdmissionDetails);

    List<ChangeAdmissionDetails> changeAdmissionDetailsList();
}
