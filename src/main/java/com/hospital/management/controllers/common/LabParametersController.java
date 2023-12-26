package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.LabParameters;
import com.hospital.management.service.LabParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lab/parameters")
public class LabParametersController {

    @Autowired
    LabParametersService labParametersService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<LabParameters>> labParametersDataList() {
        List<LabParameters> labParametersList = labParametersService.labParametersList();
        return  ResponseEntity.ok(labParametersList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<LabParameters> saveLabParameters(@RequestBody @Validated LabParameters labParameters){
        labParametersService.save(labParameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<LabParameters> updateLabParameters(@RequestBody @Validated LabParameters labParameters){
        labParametersService.save(labParameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
