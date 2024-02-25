package com.hospital.management.controllers;

import com.hospital.management.entities.commom.PatientAppointment;
import com.hospital.management.service.PatientAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient/appointment")
public class AppointmentController {

    @Autowired
    PatientAppointmentService patientAppointmentService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<PatientAppointment>> patientAppointmentList() {
        List<PatientAppointment> patientAppointmentDataList = patientAppointmentService.patientAppointmentList();
        return  ResponseEntity.ok(patientAppointmentDataList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<PatientAppointment> saveInsuranceComp(@RequestBody @Validated PatientAppointment patientAppointment){
        patientAppointmentService.save(patientAppointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<PatientAppointment> updateAuthorization(@RequestBody @Validated PatientAppointment patientAppointment){
        patientAppointmentService.save(patientAppointment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
