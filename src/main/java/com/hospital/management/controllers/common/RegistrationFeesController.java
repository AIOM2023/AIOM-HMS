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

  /*  @GetMapping
    public ResponseEntity<List<RegistrationFees>> systemParamsList() {
        List<RegistrationFees> registrationFeesList =registrationFeesService.insuranceCompList();
        return  ResponseEntity.ok(registrationFeesList);

    }*/
    @PostMapping("/save")
    public ResponseEntity<RegistrationFees> saveRegistrationFees(@RequestBody @Validated RegistrationFees registrationFees){
         registrationFees = registrationFeesService.saveregistrationFees(registrationFees);
        return new ResponseEntity<>(registrationFees,HttpStatus.OK);
    }

    @PutMapping("/update/{regFeesId}")
     public ResponseEntity<RegistrationFees> updateRegistrationFees(@RequestBody @Validated RegistrationFees registrationFees,@PathVariable("regFeesId") Integer regFeesId){
         registrationFees =registrationFeesService.updateregistrationFees(registrationFees,regFeesId);
        return new ResponseEntity<>(registrationFees,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RegistrationFees>> registrationFeesList() {
        List<RegistrationFees> registrationFeesList = registrationFeesService.registrationFeesList();
        return  ResponseEntity.ok(registrationFeesList);

    }

    @GetMapping("/{registrationFeesId}")
    public ResponseEntity<RegistrationFees> getRegistrationFeesById(@PathVariable("registrationFeesId") Integer regFeesId) {
        RegistrationFees registrationFees = registrationFeesService.findRegistrationFeesId(regFeesId);
        return ResponseEntity.ok(registrationFees);

    }

    @DeleteMapping("/delete/{registrationFeesId}")
    public ResponseEntity<String> deleteTariffById(@PathVariable("registrationFeesId") Integer regFeesId){
        String successMsg = registrationFeesService.deleteRegistrationFeesById(regFeesId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
