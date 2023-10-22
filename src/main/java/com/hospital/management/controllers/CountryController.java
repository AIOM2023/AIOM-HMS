package com.hospital.management.controllers;


import com.hospital.management.entities.CountryModel;
import com.hospital.management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryModel>> getAllCountryNames() {
        List<CountryModel> allCountryNames = countryService.getAllCountryNames();
        return ResponseEntity.ok(allCountryNames);
    }
}
