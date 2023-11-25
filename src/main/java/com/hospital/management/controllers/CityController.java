package com.hospital.management.controllers;

import com.hospital.management.entities.City;
import com.hospital.management.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<List<City>> getAllCityNames() {
        List<City> allCityNames = cityService.getAllCityNames();
        return ResponseEntity.ok(allCityNames);
    }
    
}
