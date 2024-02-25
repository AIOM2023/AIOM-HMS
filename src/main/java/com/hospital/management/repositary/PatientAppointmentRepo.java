package com.hospital.management.repositary;

import com.hospital.management.entities.commom.PatientAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientAppointmentRepo extends JpaRepository<PatientAppointment,Integer> {
}
