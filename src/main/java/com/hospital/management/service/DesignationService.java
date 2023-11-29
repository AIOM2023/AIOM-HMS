package com.hospital.management.service;

import com.hospital.management.entities.commom.Designation;

import java.util.List;

public interface DesignationService {

    void save(Designation designation);

    void update(Designation designation);

    List<Designation> designationList();
}
