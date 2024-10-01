package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Bed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedDetailsRepo extends JpaRepository<Bed,Long> {
}
