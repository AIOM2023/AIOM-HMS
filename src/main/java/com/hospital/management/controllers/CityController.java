package com.hospital.management.controllers;

import com.hospital.management.entities.CityModel;
import com.hospital.management.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityModel>> getAllCityNames() {
        List<CityModel> allCityNames = cityService.getAllCityNames();
        return ResponseEntity.ok(allCityNames);
    }
    
}
