package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Designation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DesignationRepo extends JpaRepository<Designation, Integer > {

   /* @Query(value = "SELECT * FROM designation t WHERE t.status = 0", nativeQuery = true)
    List<Designation> findAllDesignation();*/
   @Query(value = "SELECT * FROM designation WHERE designation like %?1% OR description like %?1% " +
           "AND status = 0 ORDER BY ?#{#pageable}",
           nativeQuery = true)
   Page<Designation> findAllDesignations(String search, Pageable pageable);



    Optional<Designation> findByDesignationIdAndStatus(Long designationId, Integer status);

    @Query(value = "UPDATE designation SET status = 1 WHERE designation_id = :designationId", nativeQuery = true)
    @Modifying
    void deleteDesignationById(@Param("designationId") Long designationId);

    @Query(value = "SELECT max(designation_id) FROM designation", nativeQuery = true)
    Long getMaxId();

}


