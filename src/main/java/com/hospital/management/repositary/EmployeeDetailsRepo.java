package com.hospital.management.repositary;

import com.hospital.management.entities.commom.EmployeeDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDetails, Integer > {

    @Query(value = "SELECT * FROM employee_details WHERE employee_code like %?1% OR employment_type like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<EmployeeDetails> findAllEmployeeDetails(String search, Pageable pageable);

    Optional<EmployeeDetails> findByEmployeeIdAndStatus(Long employeeId, Integer status);

    @Query(value = "UPDATE employee_details SET status = 1 WHERE employee_id = :employeeId", nativeQuery = true)
    @Modifying
    void deleteEmployeeDetailsById(@Param("employeeId") Long employeeId);

    @Query(value = "SELECT max(employee_id) FROM employee_details", nativeQuery = true)
    Long getMaxId();
}
