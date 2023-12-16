package com.hospital.management.repositary;

import com.hospital.management.entities.commom.InsuranceComp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceCompRepo extends JpaRepository<InsuranceComp, Integer > {
}