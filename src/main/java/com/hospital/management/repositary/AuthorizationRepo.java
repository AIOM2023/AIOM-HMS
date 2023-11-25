package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepo extends JpaRepository<Authorization, Integer> {
}
