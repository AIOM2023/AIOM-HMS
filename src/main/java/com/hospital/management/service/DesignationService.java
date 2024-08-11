package com.hospital.management.service;

import com.hospital.management.entities.commom.Designation;

import java.util.List;

public interface DesignationService {

    Designation saveDesignation(Designation designation);

    Designation updateDesignation(Designation designation,Integer designationId);

    List<Designation> designationList();
    Designation findDesignationById(Integer designationId);
    String deleteDesignationById(Integer designationId);

}
