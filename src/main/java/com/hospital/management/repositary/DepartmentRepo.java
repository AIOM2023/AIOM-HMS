package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Integer > {

    @Query(value = "SELECT * FROM department t WHERE t.status = 0", nativeQuery = true)
    List<Department> findAllDepartment();


    Optional<Department> findByDepartmentIdAndStatus(Integer departmentId, Integer status);

    @Query(value = "UPDATE department SET status = 1 WHERE department_id = :departmentId", nativeQuery = true)
    @Modifying
    void deleteDepartmentById(@Param("departmentId") Integer departmentId);
}