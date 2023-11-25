package com.hospital.management.controllers;

import com.hospital.management.entities.StateModel;
import com.hospital.management.repositary.StateRepo;
import com.hospital.management.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private StateRepo stateRepo;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<List<StateModel>> getAllStateNames() {
        List<StateModel> allStateNames = stateService.getAllStateNames();
        return ResponseEntity.ok(allStateNames);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/state")
    public StateModel saveState(@RequestBody StateModel saveState){
        return stateRepo.save(saveState);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("State/{id}")
    public Optional<StateModel> getState(@PathVariable Integer id){
        return stateRepo.findById(id);
    }


}
