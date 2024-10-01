package com.hospital.management.controllers.common;

import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
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

import java.util.ArrayList;
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
        List<SystemParametersMain> systemParametersMainList = new ArrayList<>();
        try {
            systemParametersMainList = systemParametersMainService.getSystemParametersMainList();
            if (!systemParametersMainList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters Main List", systemParametersMainList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "NO System Parameters Main Records found", systemParametersMainList), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", systemParametersMainList), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sysParamsMainId")
    public ResponseEntity<GenericResponse<SystemParametersMain>> systemParamsMainListById(@RequestParam Long paramsMainId) {
        SystemParametersMain systemMainParams = new SystemParametersMain();
        try {
            logger.info("System Parameters List By Id");
            systemMainParams = systemParametersMainService.getSystemParametersMainListById(paramsMainId);
            if (systemMainParams != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters Main List By Id", systemMainParams), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters Main List with this Id", systemMainParams), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters Main not found with the given Id", systemMainParams), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", systemMainParams), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<SystemParametersMain>> saveSystemMainParameters(@Valid @RequestBody SystemParametersMain systemMainParameters) {
        SystemParametersMain saveSystemParamsMain = new SystemParametersMain();
        try {
            saveSystemParamsMain = systemParametersMainService.save(systemMainParameters);

            if (saveSystemParamsMain != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "System Parameters Main Created Successfully", saveSystemParamsMain), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "System Parameters Main Not Created", saveSystemParamsMain), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "System Parameter Main Name Already Exists", saveSystemParamsMain), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveSystemParamsMain), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<SystemParametersMain>> updateSystemParametersMain(@RequestBody @Validated SystemParametersMain systemMainParameters) {
        SystemParametersMain updateSystemParamsMain = new SystemParametersMain();
        try {
             updateSystemParamsMain = systemParametersMainService.update(systemMainParameters);
            if (updateSystemParamsMain != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters Updated Successfully", updateSystemParamsMain), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "System Parameters Not Updated", updateSystemParamsMain), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "System Parameter with this Name Already Exists", updateSystemParamsMain), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateSystemParamsMain), HttpStatus.OK);
        }
    }
}
