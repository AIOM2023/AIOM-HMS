package com.hospital.management.repositary;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer > {
}