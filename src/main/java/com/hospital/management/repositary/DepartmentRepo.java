package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Integer > {


    @Query(value = "SELECT * FROM department WHERE dept_code like %?1% OR dept_name like %?1% " +
            "AND status = 0 ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<Department> findAllDepartment(String search, Pageable pageable);



    Optional<Department> findByDepartmentIdAndStatus(Integer departmentId, Integer status);

    @Query(value = "UPDATE department SET status = 1 WHERE department_id = :departmentId", nativeQuery = true)
    @Modifying
    void deleteDepartmentById(@Param("departmentId") Integer departmentId);

    @Query(value = "SELECT max(department_id) FROM department", nativeQuery = true)
    Long getMaxId();
}