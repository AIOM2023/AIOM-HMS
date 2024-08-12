package com.hospital.management.controllers.common;


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

    @Autowired
    private SystemParametersMainService systemParametersMainService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<SystemParametersMain>> systemParamsMainList() {
        List<SystemParametersMain> systemMainParams = systemParametersMainService.getSystemParametersMainList();
        return new ResponseEntity<>(systemMainParams,HttpStatus.OK);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<SystemParametersMain> saveSystemMainParameters(@RequestBody @Validated SystemParametersMain systemMainParameters){
        SystemParametersMain saveSystemParamsMain = systemParametersMainService.save(systemMainParameters);
        return new ResponseEntity<>(saveSystemParamsMain,HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<SystemParametersMain> updateAuthorization(@RequestBody @Validated SystemParametersMain systemMainParameters){
        SystemParametersMain updateSystemParamsMain = systemParametersMainService.update(systemMainParameters);
        return new ResponseEntity<>(updateSystemParamsMain,HttpStatus.OK);
    }
}
