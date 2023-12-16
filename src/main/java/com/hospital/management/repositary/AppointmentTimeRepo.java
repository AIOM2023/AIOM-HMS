package com.hospital.management.repositary;

import com.hospital.management.entities.commom.AppointmentTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentTimeRepo extends JpaRepository<AppointmentTime, Integer > {
}
