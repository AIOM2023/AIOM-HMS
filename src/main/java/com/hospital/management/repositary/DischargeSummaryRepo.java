package com.hospital.management.repositary;

import com.hospital.management.entities.commom.DischargeSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DischargeSummaryRepo extends JpaRepository<DischargeSummary, Integer > {
}
