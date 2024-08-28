package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.SystemParametersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/parameters")
public class SystemParametersController {
    private static final Logger logger = LoggerFactory.getLogger(SystemParametersController.class);

    @Autowired
    private SystemParametersService systemParametersService;

    @GetMapping
    public ResponseEntity<List<SystemParameters>> systemParamsList() {
        List<SystemParameters> systemParams = systemParametersService.getSystemParametersList();
        return  new ResponseEntity<>(systemParams,HttpStatus.OK);

    }

    @PostMapping("/save")
    public ResponseEntity<SystemParameters> saveSystemParameters(@RequestBody @Validated SystemParameters systemParameters){
        SystemParameters saveSystemParameters = systemParametersService.save(systemParameters);
        return new ResponseEntity<>(saveSystemParameters,HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<SystemParameters> updateAuthorization(@RequestBody @Validated SystemParameters systemParameters){
        SystemParameters updateSystemParameters = systemParametersService.update(systemParameters);
        return new ResponseEntity<>(updateSystemParameters,HttpStatus.OK);
    }

    @GetMapping("/sysParamByMainId")
    public ResponseEntity<GenericResponse<List<SystemParameters>>> systemParamsMainListById(@RequestParam(required = false) Integer paramsMainId){
        logger.info("System Parameters List By Id");
        List<SystemParameters> systemParamsByMainId = systemParametersService.getSystemParametersListByMainId(paramsMainId);
        if (!systemParamsByMainId.isEmpty()) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameters List found", systemParamsByMainId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "No System Parameters List found with this ID", systemParamsByMainId), HttpStatus.OK);
        }
    }


}
