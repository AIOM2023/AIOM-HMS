package com.hospital.management.repositary;

import com.hospital.management.entities.commom.FinancialDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialDetailsRepo extends JpaRepository<FinancialDetails, Integer > {
}
