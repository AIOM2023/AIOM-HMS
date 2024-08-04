package com.hospital.management.controllers;

import com.hospital.management.entities.District;
import com.hospital.management.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<District>> getAllDistricts() {
        List<District> districts = districtService.getAllDistricts();
        return ResponseEntity.ok(districts);
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<District> findDistrictById(@PathVariable("districtId") Long districtId) {
        District district = districtService.findDistrictById(districtId);
        return ResponseEntity.ok(district);
    }

    @PostMapping("/save")
    public ResponseEntity<District> saveDistrict(@RequestBody @Validated District district){
        return new ResponseEntity<>(districtService.saveDistrict(district), HttpStatus.CREATED);
    }

    @PutMapping("/update/{districtId}")
    public ResponseEntity<District> updateDistrict(@RequestBody @Validated District district, @PathVariable("districtId") Long districtId){
        return new ResponseEntity<>(districtService.updateDistrict(district, districtId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{districtId}")
    public ResponseEntity<String> deleteDistrictById(@PathVariable("districtId") Long districtId){
        return new ResponseEntity<>(districtService.deleteDistrictById(districtId), HttpStatus.OK);
    }
}
