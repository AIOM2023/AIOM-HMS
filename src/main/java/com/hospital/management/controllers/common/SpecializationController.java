package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Specialization;
import com.hospital.management.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specialization")
@CrossOrigin(origins = "http://localhost:8080")
public class SpecializationController {

    @Autowired
    private SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<List<Specialization>> getAllSpecializations() {
        List<Specialization> specializations = specializationService.getAllSpecializations();
        return  ResponseEntity.ok(specializations);
    }

    @GetMapping("/{specializationId}")
    public ResponseEntity<Specialization> getSpecializationById(@PathVariable("specializationId") Long specializationId) {
        Specialization specialization = specializationService.findSpecializationById(specializationId);
        return ResponseEntity.ok(specialization);

    }

    @PostMapping("/save")
    public ResponseEntity<Specialization> saveSpecialization(@RequestBody @Validated Specialization specialization){
        Specialization createdSpecialization = specializationService.saveSpecialization(specialization);
        return new ResponseEntity<>(createdSpecialization, HttpStatus.OK);
    }


    @PutMapping("/update/{specializationId}")
    public ResponseEntity<Specialization> updateSpecialization(@RequestBody @Validated Specialization specialization, @PathVariable ("specializationId") Long serviceGroupId){
        Specialization updatedSpecialization = specializationService.updateSpecialization(specialization, serviceGroupId);
        return new ResponseEntity<>(updatedSpecialization, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{specializationId}")
    public ResponseEntity<String> deleteSpecializationById(@PathVariable("specializationId") Long specializationId){
        String successMsg = specializationService.deleteSpecializationById(specializationId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
