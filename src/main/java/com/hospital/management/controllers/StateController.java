package com.hospital.management.controllers;

import com.hospital.management.entities.State;
import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.entities.response.StateNameId;
import com.hospital.management.entities.response.StateSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.repositary.StateRepo;
import com.hospital.management.service.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private StateRepo stateRepo;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<GenericResponse<StateSearchResult>> getAllStates(@RequestParam(name="search", required = false) String search,
                                                                           @RequestParam(defaultValue = "0",required = false) int pageNo,
                                                                           @RequestParam(defaultValue = "50",required = false) int pageSize,
                                                                           @RequestParam(name="sortBy",required = false) String sortBy,
                                                                           @RequestParam(defaultValue = "DESC",required = false) String sortOrder) {
        try{
        StateSearchResult stateSearchResult = stateService.getAllStates(search, pageNo, pageSize, sortBy, sortOrder);
            if(!stateSearchResult.getData().isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(),true ,"State Records found", stateSearchResult), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(),true, "State Records not found", stateSearchResult), HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            StateSearchResult StateSearchResultList = new StateSearchResult();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "Something went wrong",StateSearchResultList),HttpStatus.BAD_REQUEST);
        }
    }

   /* @GetMapping("/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<State> findStateById(@PathVariable("stateId") Long stateId) {
        State state = stateService.findStateById(stateId);
        return ResponseEntity.ok(state);
    }*/
    @GetMapping("/stateId")
    public ResponseEntity<GenericResponse<List<State>>> findStateById(@RequestParam(required = false) Long stateId){
        log.info("State List By Id");

        List<State> stateList = new ArrayList<>();
        try {
            stateList = stateService.findStateById(stateId);
            if (!stateList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "State List By Id", stateList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No State List with this Id", stateList), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No State found with the given Id", stateList), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", stateList), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<State>>> getAllStateList(@RequestParam(required = false) List<Long> stateIds){
        try {
            List<State> stateList = stateService.stateListAll(stateIds);

            if (!stateList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "State Records found", stateList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "State Records not found", stateList), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<State> stateEmptyList = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", stateEmptyList), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<State>> saveState(@RequestBody State saveState){

        State state = new State();
        try {
            state = stateService.saveState(saveState);
            if (state != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "state Created Successfully", state), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "state Not Created", state), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "state Name Already Exists", saveState), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", state), HttpStatus.OK);

        }
    }

    @PutMapping("/update/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<State>> updateState(@RequestBody @Validated State state, @PathVariable("stateId") Long stateId){
        State updateState = new State();
        try {
            updateState= stateService.updateState(state, stateId);
            if (updateState != null) {
                    return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "city Updated Successfully", updateState), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "city Not Updated", updateState), HttpStatus.OK);
                }
            } catch (DuplicateEntryException exception) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "city Name Already Exists", state), HttpStatus.OK);
            } catch (Exception ex) {
                System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateState), HttpStatus.OK);
            }
    }

    @DeleteMapping("/delete/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteStateById(@PathVariable("stateId") Long stateId){
        return new ResponseEntity<>(stateService.deleteStateById(stateId), HttpStatus.OK);
    }

    @GetMapping("/names/countryIds")
    public ResponseEntity<GenericResponse<List<State>>> getAllStateNamesByCountry(@RequestParam(required = false) List<Long> countryIds){
        List<State> stateIds = new ArrayList<>();
        try {
            stateIds = stateService.getAllStateNames(countryIds);
            if (!stateIds.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "State List By Id", stateIds), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No State List with this Id", stateIds), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No State found with the given Id", stateIds), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", stateIds), HttpStatus.BAD_REQUEST);
        }

    }

}
