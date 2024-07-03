package com.hospital.management.controllers;


import com.hospital.management.entities.Country;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    CountryRepo countryRepo;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Country>> getAllCountryNames() {
        List<Country> allCountryNames = countryService.getAllCountryNames();
        return ResponseEntity.ok(allCountryNames);
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Country> saveCountry(@RequestBody @Validated Country country){
        return new ResponseEntity<>(countryService.saveCountry(country), HttpStatus.CREATED);
    }

    @PutMapping("/update/{countryId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Country> updateCountry(@RequestBody @Validated Country country, @PathVariable("countryId") Integer countryId){
        return new ResponseEntity<>(countryService.updateCountry(country, countryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{countryId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteCountryById(@PathVariable("countryId") Integer countryId){
        return new ResponseEntity<>(countryService.deleteCountryById(countryId), HttpStatus.OK);
    }
}
