package com.hospital.management.service;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.response.DesignationSearchResult;

public interface DesignationService {

    Designation saveDesignation(Designation designation);

    Designation updateDesignation(Designation designation,Long designationId);

   DesignationSearchResult designationList(String search, int pageNo, int pageSize, String sortBy, String sortOrder);
    Designation findDesignationById(Long designationId);
    String deleteDesignationById(Long designationId);

}
