package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Branches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchesRepo extends JpaRepository<Branches, Integer > {
}
