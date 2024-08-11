package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.entities.commom.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DesignationRepo extends JpaRepository<Designation, Integer > {

    @Query(value = "SELECT * FROM designation t WHERE t.status = 0", nativeQuery = true)
    List<Designation> findAllDesignation();


    Optional<Designation> findByDesignationIdAndStatus(Integer designationId, Integer status);

    @Query(value = "UPDATE designation SET status = 1 WHERE designation_id = :designationId", nativeQuery = true)
    @Modifying
    void deleteDesignationById(@Param("designationId") Integer designationId);
}


