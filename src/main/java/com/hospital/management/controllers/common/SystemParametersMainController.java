package com.hospital.management.controllers.common;

import com.hospital.management.model.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hospital.management.entities.commom.SystemParametersMain;
import com.hospital.management.service.SystemParametersMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/parameters/main")
public class SystemParametersMainController {

    private static final Logger logger = LoggerFactory.getLogger(SystemParametersMainController.class);

    @Autowired
    private SystemParametersMainService systemParametersMainService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<List<SystemParametersMain>>> systemParamsMainList() {
        logger.info("System Parameters List");
        List<SystemParametersMain> systemMainParams = systemParametersMainService.getSystemParametersMainList();
        if (!systemMainParams.isEmpty()) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameters List found", systemMainParams), HttpStatus.OK);
        } else if (systemMainParams.isEmpty()) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "No System Parameters List found", systemMainParams), HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(), "System Parameters List not found", null), HttpStatus.NOT_FOUND);
        }

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<GenericResponse<SystemParametersMain>> saveSystemMainParameters(@RequestBody @Validated SystemParametersMain systemMainParameters){
        SystemParametersMain saveSystemParamsMain = systemParametersMainService.save(systemMainParameters);

        if (saveSystemParamsMain != null) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameter Saved Successfully", saveSystemParamsMain), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "System Parameter Bad Request", null), HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<GenericResponse<SystemParametersMain>> updateSystemParametersMain(@RequestBody @Validated SystemParametersMain systemMainParameters){
        SystemParametersMain updateSystemParamsMain = systemParametersMainService.update(systemMainParameters);
        if (updateSystemParamsMain != null) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameter Updated Successfully", updateSystemParamsMain), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "System Parameter Bad Request", null), HttpStatus.BAD_REQUEST);
        }
    }
}
