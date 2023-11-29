package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepo extends JpaRepository<Designation, Integer > {
}


