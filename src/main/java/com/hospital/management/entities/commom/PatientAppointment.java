package com.hospital.management.entities.commom;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name= "patient_appointment")
public class PatientAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "appointment_id")
    private Integer appointmentId;

    @Column(name= "appointment_number")
    private Integer appointmentNumber;

    @Column(name= "patient_id")
    private Integer patientId;

    @Column(name= "doctor_id")
    private Integer doctorId;

    @Column(name= "appointment_date")
    private OffsetDateTime appointmentDate;

    @Column(name= "appointment_time")
    private OffsetDateTime appointmentTime;

    @Column(name= "appointment_reason")
    private String appointmentReason;

    @Column(name= "appointment_status")
    private String appointmentStatus;

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

    @Column(name= "delete_appointment")
    private Integer deleteAppointment;

    @Column(name= "in_active")
    private Integer inActive;

}
