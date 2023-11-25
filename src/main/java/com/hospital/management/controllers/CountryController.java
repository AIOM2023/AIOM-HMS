package com.hospital.management.controllers;


import com.hospital.management.entities.Country;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Country saveCountry(@RequestBody @Validated com.hospital.management.model.Country country){
        Country countryModel= new Country();
        countryModel.setCountryname(country.getCountryName());
        return countryRepo.save(countryModel);
    }

    @PostMapping("/update")
    @CrossOrigin(origins = "http://localhost:8080")
    public Country saveCity(@RequestBody @Validated com.hospital.management.model.Country country){
        Country countryModel= new Country();
        countryModel.setCountryid(country.getCountryId());
        countryModel.setCountryname(country.getCountryName());
        return countryRepo.save(countryModel);
    }
}
