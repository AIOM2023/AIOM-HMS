package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Branches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BranchesRepo extends JpaRepository<Branches, Integer > {

    @Query(value = "SELECT * FROM branches r WHERE r.status = 0", nativeQuery = true)
    List<Branches> findAllBranch();


    Optional<Branches> findBybranchIdAndStatus(Long branchId, Integer status);

    @Query(value = "UPDATE branches SET status = 1 WHERE branche_id = :branchId", nativeQuery = true)
    @Modifying
    void deleteBranchById(@Param("branchId") Long dischargeId);

    @Query(value = "SELECT max(branche_id) FROM branches", nativeQuery = true)
    Long getMaxId();
}
