package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Designation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {

    @Query(value = "SELECT d FROM Designation d WHERE d.status = 0")
   Page<Designation> findAllDesignations(String search, Pageable pageable);

    @Query(value = "SELECT d FROM Designation d where d.status=0 and d.designationId = :designationId")
    List<Designation> findByDesignationId(Long designationId);

    @Query(value = "SELECT d FROM Designation d where d.status=0 ORDER BY d.designation ASC")
    List<Designation> findAllDesifnationList();

    Designation findByDesignation(String Designation);

    @Query(value = "SELECT max(d.designationId) FROM Designation d")
    Long getMaxId();

}


