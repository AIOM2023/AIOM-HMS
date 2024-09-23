package com.hospital.management.controllers;



import com.hospital.management.entities.commom.NurseStation;
import com.hospital.management.entities.response.NurseStationSearchResult;
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
    public ResponseEntity<NurseStationSearchResult> getAllNurseStationDetails(@RequestParam(name="search") String search,
                                                                              @RequestParam(defaultValue = "0") int pageNo,
                                                                              @RequestParam(defaultValue = "50") int pageSize,
                                                                              @RequestParam(name="sortBy") String sortBy,
                                                                              @RequestParam(defaultValue = "DESC") String sortOrder ) {
        NurseStationSearchResult nurseStationSearchResult = nurseStationService.getAllNurseStation(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(nurseStationSearchResult);

    }

    @GetMapping("/{nurseStationId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<NurseStation> findNurseStationId(@PathVariable("nurseStationId") Long nurseStationId) {
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
    public ResponseEntity<NurseStation> updateNurseStation(@RequestBody @Validated NurseStation nurseStation, @PathVariable("nurseStationId") Long nurseStationId){
        return new ResponseEntity<>(nurseStationService.updatenurseStation(nurseStation, nurseStationId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{nurseStationId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteCountryById(@PathVariable("nurseStationId") Long nurseStationId){
        return new ResponseEntity<>(nurseStationService.deleteNurseStationById(nurseStationId), HttpStatus.OK);
    }
}
