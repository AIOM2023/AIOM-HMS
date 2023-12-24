package com.hospital.management.repositary;

import com.hospital.management.entities.commom.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffRepo extends JpaRepository<Tariff, Integer> {
}
