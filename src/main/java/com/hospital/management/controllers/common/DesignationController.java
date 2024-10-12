package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Designation;
import com.hospital.management.entities.search.DesignationSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.DesignationService;
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
@RequestMapping("/designation")
public class DesignationController {

    @Autowired
    DesignationService designationService;

    @GetMapping
    public ResponseEntity<GenericResponse<DesignationSearchResult>> designationList(@RequestParam(name = "search") String search,
                                                                                    @RequestParam(defaultValue = "0") int pageNo,
                                                                                    @RequestParam(defaultValue = "50") int pageSize,
                                                                                    @RequestParam(name = "sortBy") String sortBy,
                                                                                    @RequestParam(defaultValue = "DESC") String sortOrder) {

        try {
            DesignationSearchResult designationSearchResult = designationService.designationList(search, pageNo, pageSize, sortBy, sortOrder);
            if (!designationSearchResult.getData().isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Designation  Records found", designationSearchResult), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Designation Records not found", designationSearchResult), HttpStatus.OK);
            }
        } catch (Exception ex) {
            DesignationSearchResult designationSearchResult = new DesignationSearchResult();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", designationSearchResult), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<GenericResponse<List<Designation>>> findDesignationById(@RequestParam Long designationId) {
        log.info("Designation List By Id");
        List<Designation> designationListById = new ArrayList<>();
        try {
            designationListById = designationService.findDesignationById(designationId);
            if (!designationListById.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Designation List By Id", designationListById), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Designation List with this Id", designationListById), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Designation found with the given Id", designationListById), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", designationListById), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<Designation>>> getAllSystemRequestList() {
        try {
            List<Designation> designationList = designationService.designationListAll();

            if (!designationList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Designation Records found", designationList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Designation Records not found", designationList), HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex) {
            List<Designation> designationList = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", designationList), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<Designation>> saveDesignation(@RequestBody @Validated Designation designation) {
        Designation saveDesignation= new Designation();
        try {
            saveDesignation = designationService.saveDesignation(designation);
            if (saveDesignation != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "Designation Created Successfully", saveDesignation), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Designation Not Created", new Designation()), HttpStatus.OK);
            }

        }catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Designation Name Already Exists", saveDesignation), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveDesignation), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<Designation>> updateDesignation(@RequestBody @Validated Designation designation) {

        Designation updateDesignation= new Designation();
        try {
            updateDesignation = designationService.updateDesignation(designation);
            if (updateDesignation != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Designation Updated Successfully", updateDesignation), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Designation Not Updated", new Designation()), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Designation Name Already Exists", updateDesignation), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXCEPTION:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateDesignation), HttpStatus.OK);
        }

    }


    @PostMapping("/delete")
    public ResponseEntity<GenericResponse<Designation>> deleteDesignationById(@RequestParam Long designationId) {
        Designation deleteDesignation;
        try{
            deleteDesignation= designationService.deleteDesignationById(designationId);
            if (deleteDesignation != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Designation Deleted Successfully", deleteDesignation), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Technical Error", new Designation()), HttpStatus.OK);
            }
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(), true, "Designation With that Id Doesn't Exists", new Designation()), HttpStatus.OK);
        }
    }
}
