package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {


    @Query(value = "SELECT d FROM Department d where d.status=0")
    Page<Department> findAllDepartment(String search, Pageable pageable);

    @Query(value = "SELECT d FROM Department d where d.status=0 and d.departmentId = :departmentId")
    List<Department> findByDepartmentId(Long departmentId);

    Department findByDepartmentName(String departmentName);

    @Query(value = "SELECT d FROM Department d where d.status=0 ORDER BY d.departmentName ASC")
    List<Department> findAllDepartmentList();

    @Query(value = "SELECT max(d.departmentId) FROM Department d")
    Long getMaxId();
}