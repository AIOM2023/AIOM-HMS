package com.hospital.management.controllers;

import com.hospital.management.entities.State;
import com.hospital.management.entities.response.CountrySearchResult;
import com.hospital.management.entities.response.StateNameId;
import com.hospital.management.entities.response.StateSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.repositary.StateRepo;
import com.hospital.management.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @Autowired
    private StateRepo stateRepo;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping
    public ResponseEntity<GenericResponse<StateSearchResult>> getAllStates( @RequestParam(name="search") String search,
                                                           @RequestParam(defaultValue = "0") int pageNo,
                                                           @RequestParam(defaultValue = "50") int pageSize,
                                                           @RequestParam(name="sortBy") String sortBy,
                                                           @RequestParam(defaultValue = "DESC") String sortOrder) {
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

    @GetMapping("/{stateId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<State> findStateById(@PathVariable("stateId") Long stateId) {
        State state = stateService.findStateById(stateId);
        return ResponseEntity.ok(state);
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

    @GetMapping("/names/{countryId}")
    public ResponseEntity<List<StateNameId>> getAllStateNamesByCountry(@PathVariable("countryId") Long countryId){
        return new ResponseEntity<>(stateService.getAllStateNames(countryId), HttpStatus.OK);
    }

}
