package com.hospital.management.controllers;


import com.hospital.management.entities.Country;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.entities.search.SystemParametersSearchList;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.model.GenericResponse;
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
    public ResponseEntity<GenericResponse<CountrySearchResult>> getAllCountries(
            @RequestParam(name="search") String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortOrder ) {
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
    public ResponseEntity<Country> findCountryById(@PathVariable("countryId") Long countryId) {
        Country country = countryService.findCountryById(countryId);
        return ResponseEntity.ok(country);
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
