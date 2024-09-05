package com.hospital.management.repositary;

import com.hospital.management.entities.commom.EmployeeDeatils;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDetailsRepo extends JpaRepository<EmployeeDeatils, Integer > {
}
