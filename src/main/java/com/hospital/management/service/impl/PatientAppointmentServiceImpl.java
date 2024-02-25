package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.PatientAppointment;
import com.hospital.management.repositary.PatientAppointmentRepo;
import com.hospital.management.service.PatientAppointmentService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientAppointmentServiceImpl implements PatientAppointmentService {

    @Autowired
    PatientAppointmentRepo patientAppointmentRepo;

    @Override
    public void save(PatientAppointment patientAppointment) {
        patientAppointmentRepo.save(patientAppointment);
    }

    @Override
    public void update(PatientAppointment patientAppointment) {
        patientAppointmentRepo.save(patientAppointment);
    }

    @Override
    public List<PatientAppointment> patientAppointmentList() {
        return patientAppointmentRepo.findAll();
    }
}
