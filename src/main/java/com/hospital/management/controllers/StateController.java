package com.hospital.management.controllers;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.State;
import com.hospital.management.entities.response.StateSearchResult;
import com.hospital.management.repositary.StateRepo;
import com.hospital.management.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<StateSearchResult> getAllStates( @RequestParam(name="search") String search,
                                                           @RequestParam(defaultValue = "0") int pageNo,
                                                           @RequestParam(defaultValue = "50") int pageSize,
                                                           @RequestParam(name="sortBy") String sortBy,
                                                           @RequestParam(defaultValue = "ASC") String sortOrder ) {
        StateSearchResult stateSearchResult = stateService.getAllStates(search, pageNo, pageSize, sortBy, sortOrder);
        return ResponseEntity.ok(stateSearchResult);
    }

    @GetMapping("/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<State> findStateById(@PathVariable("stateId") Long stateId) {
        State state = stateService.findStateById(stateId);
        return ResponseEntity.ok(state);
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<State> saveState(@RequestBody State saveState){

        return new ResponseEntity<>(stateService.saveState(saveState), HttpStatus.CREATED);
    }

    @PutMapping("/update/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<State> updateState(@RequestBody @Validated State state, @PathVariable("stateId") Long stateId){
        return new ResponseEntity<>(stateService.updateState(state, stateId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteStateById(@PathVariable("stateId") Long stateId){
        return new ResponseEntity<>(stateService.deleteStateById(stateId), HttpStatus.OK);
    }

}
