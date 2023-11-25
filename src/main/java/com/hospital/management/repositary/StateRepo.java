package com.hospital.management.repositary;

import com.hospital.management.entities.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<StateModel, Integer> {
}
