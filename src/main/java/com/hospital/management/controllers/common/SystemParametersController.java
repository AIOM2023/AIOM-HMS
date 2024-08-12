package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.service.SystemParametersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/parameters")
public class SystemParametersController {

    @Autowired
    private SystemParametersService systemParametersService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<SystemParameters>> systemParamsList() {
        List<SystemParameters> systemParams = systemParametersService.getSystemParametersList();
        return  new ResponseEntity<>(systemParams,HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<SystemParameters> saveSystemParameters(@RequestBody @Validated SystemParameters systemParameters){
        SystemParameters saveSystemParameters = systemParametersService.save(systemParameters);
        return new ResponseEntity<>(saveSystemParameters,HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<SystemParameters> updateAuthorization(@RequestBody @Validated SystemParameters systemParameters){
        SystemParameters updateSystemParameters = systemParametersService.update(systemParameters);
        return new ResponseEntity<>(updateSystemParameters,HttpStatus.OK);
    }
}
