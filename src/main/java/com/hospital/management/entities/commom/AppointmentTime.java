package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
@Entity
@Table(name= "appointment_time")
public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "app_time_id")
    private Integer appTimeId;

    @Column(name= "doctor_code")
    private String doctorCode;

    @Column(name= "appointment_minutes")
    private Integer appointmentMinutes;

    @Column(name= "created_date")
    private OffsetDateTime createdDate;

    @Column(name= "created_by")
    private String createdBy;

    @Column(name= "created_id")
    private String createdId;

    @Column(name= "modified_date")
    private OffsetDateTime modifiedDate;

    @Column(name= "modified_by")
    private String modifiedBy;

    @Column(name= "modified_id")
    private String modifiedId;

    @Column(name= "del_appointment_time")
    private Integer delAppointmentTime;

    @Column(name= "in_active")
    private Integer inActive;
}
