package com.hospital.management.service;


import com.hospital.management.entities.commom.AppointmentTime;

import java.util.List;

public interface AppointmentTimeService {

    void save(AppointmentTime appointmentTime);
    void update(AppointmentTime appointmentTime);
    List<AppointmentTime> appointmentTimeList();
}
