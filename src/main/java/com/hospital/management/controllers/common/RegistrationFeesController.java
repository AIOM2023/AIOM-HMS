package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.RegistrationFees;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.entities.response.RegistrationFeesSearchResult;
import com.hospital.management.service.RegistrationFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration/fees")
public class RegistrationFeesController {

    @Autowired
    RegistrationFeesService registrationFeesService;


    @PostMapping("/save")
    public ResponseEntity<RegistrationFees> saveRegistrationFees(@RequestBody @Validated RegistrationFees registrationFees){
         registrationFees = registrationFeesService.saveregistrationFees(registrationFees);
        return new ResponseEntity<>(registrationFees,HttpStatus.OK);
    }

    @PutMapping("/update/{regFeesId}")
     public ResponseEntity<RegistrationFees> updateRegistrationFees(@RequestBody @Validated RegistrationFees registrationFees,@PathVariable("regFeesId") Long regFeesId){
         registrationFees =registrationFeesService.updateregistrationFees(registrationFees,regFeesId);
        return new ResponseEntity<>(registrationFees,HttpStatus.OK);
    }

   /* @GetMapping
    public ResponseEntity<RegistrationFeesSearchResult> registrationFeesList() {
        @RequestParam(name="search") String search,
        @RequestParam(defaultValue = "0") int pageNo,
        @RequestParam(defaultValue = "50") int pageSize,
        @RequestParam(name="sortBy") String sortBy,
        @RequestParam(defaultValue = "ASC") String sortOrder ) {
            CountrySearchResult countrySearchResult = countryService.getAllCountries(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(registrationFeesList);

    }*/

    @GetMapping
    public ResponseEntity<RegistrationFeesSearchResult> registrationFeesList(
            @RequestParam(name="search") String search,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "50") int pageSize,
            @RequestParam(name="sortBy") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder ) {
        RegistrationFeesSearchResult registrationFeesSearchResult = registrationFeesService.registrationFeesList(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(registrationFeesSearchResult);
    }

    @GetMapping("/{registrationFeesId}")
    public ResponseEntity<RegistrationFees> getRegistrationFeesById(@PathVariable("registrationFeesId") Long regFeesId) {
        RegistrationFees registrationFees = registrationFeesService.findRegistrationFeesId(regFeesId);
        return ResponseEntity.ok(registrationFees);

    }

    @DeleteMapping("/delete/{registrationFeesId}")
    public ResponseEntity<String> deleteTariffById(@PathVariable("registrationFeesId") Long regFeesId){
        String successMsg = registrationFeesService.deleteRegistrationFeesById(regFeesId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
