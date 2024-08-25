package com.hospital.management.controllers;



import com.hospital.management.entities.commom.NurseStation;
import com.hospital.management.repositary.NurseStationRepo;

import com.hospital.management.service.impl.NurseStationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurseStation")
public class NurseStationController {

    @Autowired
    private NurseStationServiceImpl nurseStationService;

    @Autowired
    NurseStationRepo nurseStationRepo;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<NurseStation>> getAllNurseStationDetails() {
        List<NurseStation> allNurseStation = nurseStationService.getAllNurseStation();
        return ResponseEntity.ok(allNurseStation);
    }

    @GetMapping("/{nurseStationId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<NurseStation> findNurseStationId(@PathVariable("nurseStationId") Integer nurseStationId) {
        NurseStation nurseStation = nurseStationService.findNurseStationId(nurseStationId);
        return ResponseEntity.ok(nurseStation);
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<NurseStation> saveNurseStation(@RequestBody @Validated NurseStation nurseStation){
        return new ResponseEntity<>(nurseStationService.saveNurseStation(nurseStation), HttpStatus.CREATED);
    }

    @PutMapping("/update/{nurseStationId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<NurseStation> updateNurseStation(@RequestBody @Validated NurseStation nurseStation, @PathVariable("nurseStationId") Integer nurseStationId){
        return new ResponseEntity<>(nurseStationService.updatenurseStation(nurseStation, nurseStationId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{nurseStationId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteCountryById(@PathVariable("nurseStationId") Integer nurseStationId){
        return new ResponseEntity<>(nurseStationService.deleteNurseStationById(nurseStationId), HttpStatus.OK);
    }
}
