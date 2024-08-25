package com.hospital.management.controllers;


import com.hospital.management.entities.Country;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    CountryRepo countryRepo;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<CountrySearchResult> getAllCountries(
            @RequestParam(name="search") String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder ) {
        CountrySearchResult countrySearchResult = countryService.getAllCountries(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(countrySearchResult);
    }

    @GetMapping("/{countryId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Country> findCountryById(@PathVariable("countryId") Long countryId) {
        Country country = countryService.findCountryById(countryId);
        return ResponseEntity.ok(country);
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Country> saveCountry(@RequestBody @Validated Country country){
        return new ResponseEntity<>(countryService.saveCountry(country), HttpStatus.CREATED);
    }

    @PutMapping("/update/{countryId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Country> updateCountry(@RequestBody @Validated Country country, @PathVariable("countryId") Long countryId){
        return new ResponseEntity<>(countryService.updateCountry(country, countryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{countryId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteCountryById(@PathVariable("countryId") Long countryId){
        return new ResponseEntity<>(countryService.deleteCountryById(countryId), HttpStatus.OK);
    }
}
