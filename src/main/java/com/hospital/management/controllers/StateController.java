package com.hospital.management.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.hospital.management.entities.StateModel;
import com.hospital.management.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<StateModel>> getAllStateNames() {
        List<StateModel> allStateNames = stateService.getAllStateNames();
        return ResponseEntity.ok(allStateNames);
    }

}
