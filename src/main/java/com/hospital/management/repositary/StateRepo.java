package com.hospital.management.repositary;

import com.hospital.management.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepo extends JpaRepository<State, Integer> {
}
