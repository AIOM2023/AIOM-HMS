package com.hospital.management.repositary;

import com.hospital.management.entities.commom.RegistrationFees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationFeesRepo extends JpaRepository<RegistrationFees, Integer > {
}
