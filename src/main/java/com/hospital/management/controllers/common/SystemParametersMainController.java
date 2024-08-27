package com.hospital.management.controllers.common;

import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.model.GenericResponse;
import jakarta.validation.Valid;
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
    public ResponseEntity<GenericResponse<List<SystemParametersMain>>> systemParamsMainList() {
        logger.info("System Parameters List");
        List<SystemParametersMain> systemMainParams = systemParametersMainService.getSystemParametersMainList();
        if (!systemMainParams.isEmpty()) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameters List found", systemMainParams), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "No System Parameters List found", systemMainParams), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/sysParamsMainId")
    public ResponseEntity<GenericResponse<SystemParametersMain>> systemParamsMainListById(@RequestParam(required = false) Integer paramsMainId) {
        logger.info("System Parameters List By Id");
        SystemParametersMain systemMainParams = systemParametersMainService.getSystemParametersMainListById(paramsMainId);
        if (systemMainParams!=null) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameters List found", systemMainParams), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "No System Parameters List found", systemMainParams), HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<SystemParametersMain>> saveSystemMainParameters(@Valid @RequestBody SystemParametersMain systemMainParameters){
        try {
            SystemParametersMain saveSystemParamsMain = systemParametersMainService.save(systemMainParameters);

            if (saveSystemParamsMain != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameter Saved Successfully", saveSystemParamsMain), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "System Parameter Bad Request", null), HttpStatus.BAD_REQUEST);
            }
        }catch (DuplicateEntryException exception){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), exception.getMessage(), null), HttpStatus.CONFLICT);
        }catch (Exception ex){
            System.out.println("EXXXXXXXXXXXXXXX:"+ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<SystemParametersMain>> updateSystemParametersMain(@RequestBody @Validated SystemParametersMain systemMainParameters){
        try {
            SystemParametersMain updateSystemParamsMain = systemParametersMainService.update(systemMainParameters);
            if (updateSystemParamsMain != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), "System Parameter Saved Successfully", updateSystemParamsMain), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "System Parameter Bad Request", null), HttpStatus.BAD_REQUEST);
            }
        }catch (DuplicateEntryException exception){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), exception.getMessage(), null), HttpStatus.CONFLICT);
        }catch (Exception ex){
            System.out.println("EXXXXXXXXXXXXXXX:"+ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), ex.getMessage(), null), HttpStatus.CONFLICT);
        }
    }
}
