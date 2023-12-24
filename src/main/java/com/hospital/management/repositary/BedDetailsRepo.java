package com.hospital.management.repositary;

import com.hospital.management.entities.commom.BedDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BedDetailsRepo extends JpaRepository<BedDetails,Integer> {
}
