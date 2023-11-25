package com.hospital.management.controllers.common;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.AddAuthorization;
import com.hospital.management.model.common.AddAuthorizationModel;
import com.hospital.management.repositary.CountryRepo;
import com.hospital.management.service.AddAuthorizationService;
import com.hospital.management.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add/authorization")
public class AddAuthorizationController {

    @Autowired
    private AddAuthorizationService authorizationService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<AddAuthorization>> addAuthorizationList() {
        return null;

    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public AddAuthorization saveCountry(@RequestBody @Validated AddAuthorizationModel addAuthorizationModel){

        authorizationService.save(addAuthorizationModel);
        return null;
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
