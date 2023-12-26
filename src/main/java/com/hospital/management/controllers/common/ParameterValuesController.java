package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ParameterValues;
import com.hospital.management.service.ParameterValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parameter/values")
public class ParameterValuesController {

    @Autowired
    ParameterValuesService parameterValuesService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ParameterValues>> parameterValuesList() {
        List<ParameterValues> parameterValuesList = parameterValuesService.parameterValuesList();
        return  ResponseEntity.ok(parameterValuesList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ParameterValues> saveParameterValues(@RequestBody @Validated ParameterValues parameterValues){
        parameterValuesService.save(parameterValues);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ParameterValues> updateParameterValues(@RequestBody @Validated ParameterValues parameterValues){
        parameterValuesService.save(parameterValues);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
