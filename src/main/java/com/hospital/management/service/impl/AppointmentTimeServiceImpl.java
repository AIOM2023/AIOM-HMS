package com.hospital.management.service.impl;

import com.hospital.management.entities.commom.AppointmentTime;
import com.hospital.management.repositary.AppointmentTimeRepo;
import com.hospital.management.service.AppointmentTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTimeServiceImpl implements AppointmentTimeService {

    @Autowired
    AppointmentTimeRepo appointmentTimeRepo;

    @Override
    public void save(AppointmentTime appointmentTime) {
        appointmentTimeRepo.save(appointmentTime);
    }

    @Override
    public void update(AppointmentTime appointmentTime) {
        appointmentTimeRepo.save(appointmentTime);
    }

    @Override
    public List<AppointmentTime> appointmentTimeList() {
        return appointmentTimeRepo.findAll();
    }
}
