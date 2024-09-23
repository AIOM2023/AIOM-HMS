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
    public ResponseEntity<CountrySearchResult> getAllCountries(
            @RequestParam(name="search") String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder ) {
        CountrySearchResult countrySearchResult = countryService.getAllCountries(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(countrySearchResult);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> findCountryById(@PathVariable("countryId") Long countryId) {
        Country country = countryService.findCountryById(countryId);
        return ResponseEntity.ok(country);
    }

    @PostMapping("/save")
    public ResponseEntity<Country> saveCountry(@RequestBody @Validated Country country){
        return new ResponseEntity<>(countryService.saveCountry(country), HttpStatus.CREATED);
    }

    @PutMapping("/update/{countryId}")
    public ResponseEntity<Country> updateCountry(@RequestBody @Validated Country country, @PathVariable("countryId") Long countryId){
        return new ResponseEntity<>(countryService.updateCountry(country, countryId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable("countryId") Long countryId){
        return new ResponseEntity<>(countryService.deleteCountryById(countryId), HttpStatus.OK);
    }
}
