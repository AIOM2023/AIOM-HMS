package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.RegistrationFees;
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

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<RegistrationFees>> systemParamsList() {
        List<RegistrationFees> registrationFeesList =registrationFeesService.insuranceCompList();
        return  ResponseEntity.ok(registrationFeesList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<RegistrationFees> saveInsuranceComp(@RequestBody @Validated RegistrationFees registrationFees){
        registrationFeesService.save(registrationFees);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<RegistrationFees> updateAuthorization(@RequestBody @Validated RegistrationFees registrationFees){
        registrationFeesService.update(registrationFees);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
