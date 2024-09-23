package com.hospital.management.service;

import com.hospital.management.entities.commom.EmployeeDetails;
import com.hospital.management.entities.response.EmployeeDetailsSearchResult;

public interface EmployeeDetailsService {

    EmployeeDetailsSearchResult getAllEmployeeDetails(String search, int pageNo, int pageSize, String sortBy, String sortOrder);

    EmployeeDetails findEmployeeDetailsById(Long employeeId);

    EmployeeDetails saveEmployeeDetails(EmployeeDetails employeeDetails);

    EmployeeDetails updateEmployeeDetails(EmployeeDetails employeeDetails, Long employeeId);

    String deleteEmployeeDetailsById(Long employeeId);
}
