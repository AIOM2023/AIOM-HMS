package com.hospital.management.service;

import com.hospital.management.entities.commom.PatientAppointment;

import java.util.List;

public interface PatientAppointmentService {

    void save(PatientAppointment patientAppointment);

    void update(PatientAppointment patientAppointment);

    List<PatientAppointment> patientAppointmentList();

}
