package com.hospital.management.controllers;


import com.hospital.management.entities.Country;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    CountryRepo countryRepo;
/* */
    @GetMapping
    public ResponseEntity<GenericResponse<CountrySearchResult>> getAllCountries(
                @RequestParam(name="search", required = false) String search,
                @RequestParam(defaultValue = "0",required = false) int pageNo,
                @RequestParam(defaultValue = "50",required = false) int pageSize,
                @RequestParam(name="sortBy",required = false) String sortBy,
                @RequestParam(defaultValue = "DESC",required = false) String sortOrder){
        try{
        CountrySearchResult countrySearchResult = countryService.getAllCountries(search, pageNo, pageSize, sortBy, sortOrder);
            if(!countrySearchResult.getData().isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(),true ,"Country Records found", countrySearchResult), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(),true, "Country Records not found", countrySearchResult), HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            CountrySearchResult countrySearchResultList = new CountrySearchResult();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "Something went wrong",countrySearchResultList),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<GenericResponse<List<Country>>> findCountryById(@PathVariable("countryId") Long countryId) {
      //  Country country = countryService.findCountryById(countryId);

        log.info("Country List By Id");

        List<Country> countryList = new ArrayList<>();
        try {
            countryList = countryService.findCountryById(countryId);
            if (!countryList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Country List By Id", countryList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Country List with this Id", countryList), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Country found with the given Id", countryList), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", countryList), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<Country>>> getAllSystemRequestList(){
        try {
            List<Country> countryList = countryService.countryListAll();

            if (!countryList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Country Records found", countryList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Country Records not found", countryList), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<Country> countryList = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", countryList), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<Country>> saveCountry(@RequestBody @Validated Country country) {
        Country saveCountry = new Country();
        try {
            saveCountry = countryService.saveCountry(country);
            if (saveCountry != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "Country Created Successfully", saveCountry), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Country Not Created", saveCountry), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Country Name Already Exists", country), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveCountry), HttpStatus.OK);

        }
    }


    @PutMapping("/update/{countryId}")
    public ResponseEntity<GenericResponse<Country>> updateCountry(@RequestBody @Validated Country country, @PathVariable("countryId") Long countryId){
        Country updateCountry = new Country();
        try {
        updateCountry = countryService.updateCountry(country, countryId);
            if (updateCountry != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Country Updated Successfully", updateCountry), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Country Not Updated", updateCountry), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Country Name Already Exists", country), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateCountry), HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable("countryId") Long countryId){

        return new ResponseEntity<>(countryService.deleteCountryById(countryId), HttpStatus.OK);
    }

}
