package com.hospital.management.service;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.search.DesignationSearchResult;

import java.util.List;

public interface DesignationService {

    DesignationSearchResult designationList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    List<Designation> findDesignationById(Long designationId);

    List<Designation> designationListAll();

    Designation saveDesignation(Designation designation);

    Designation updateDesignation(Designation designation);

    Designation deleteDesignationById(Long designationId);

}
