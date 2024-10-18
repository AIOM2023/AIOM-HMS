package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.DischargeFormat;
import com.hospital.management.entities.search.DischargeFormatSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.impl.DischargeFormatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/discharge/Format")
public class DischargeFormatController {

    @Autowired
    DischargeFormatService dischargeFormatService;

    @GetMapping
    public ResponseEntity<GenericResponse<DischargeFormatSearchResult>> dischargeFormatList(@RequestParam(name="search") String search,
                                                                           @RequestParam(defaultValue = "0") int pageNo,
                                                                           @RequestParam(defaultValue = "50") int pageSize,
                                                                           @RequestParam(name="sortBy") String sortBy,
                                                                           @RequestParam(defaultValue = "DESC") String sortOrder ) {
        try {
            DischargeFormatSearchResult dischargeFormatSearchResult = dischargeFormatService.dischargeFormatList(search, pageNo, pageSize, sortBy, sortOrder);
            if (!dischargeFormatSearchResult.getData().isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Discharge  Records found", dischargeFormatSearchResult), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Discharge Records not found", dischargeFormatSearchResult), HttpStatus.OK);
            }
        } catch (Exception ex) {
            DischargeFormatSearchResult dischargeFormatSearchResult = new DischargeFormatSearchResult();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", dischargeFormatSearchResult), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id")
    public ResponseEntity<GenericResponse<List<DischargeFormat>>> findDischargeFormatById(@RequestParam Long discFmtId) {
        log.info("Discharge List By Id");
        List<DischargeFormat> dischargeFormatList = new ArrayList<>();
        try {
            dischargeFormatList = dischargeFormatService.findDischargeFormatById(discFmtId);
            if (!dischargeFormatList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Discharge List By Id", dischargeFormatList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Discharge Format List with this Id", dischargeFormatList), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Discharge found with the given Id", dischargeFormatList), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", dischargeFormatList), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<DischargeFormat>>> getAllSystemRequestList(){
        try {
            List<DischargeFormat> dischargeFormatList = dischargeFormatService.dischargeFormatListAll();
            if (!dischargeFormatList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Discharge Format Records found", dischargeFormatList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Discharge Format Records not found", dischargeFormatList), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<DischargeFormat> dischargeFormatList = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", dischargeFormatList), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveDischargeFormat")
    public ResponseEntity<GenericResponse<DischargeFormat>> saveDischargeFormat(@RequestBody @Validated DischargeFormat dischargeFormat) {
        DischargeFormat saveDischargeFormat = new DischargeFormat();
        try {
            saveDischargeFormat = dischargeFormatService.saveDischargeFormat(dischargeFormat);
            if (saveDischargeFormat != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "Discharge Format Created Successfully", saveDischargeFormat), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Discharge Format Not Created", new DischargeFormat()), HttpStatus.OK);
            }

        }catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Discharge Format Name Already Exists", saveDischargeFormat), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveDischargeFormat), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<DischargeFormat>> updateDischargeFormat(@RequestBody @Validated DischargeFormat dischargeFormat){

        DischargeFormat updateDischargeFormat= new DischargeFormat();
        try {
            updateDischargeFormat = dischargeFormatService.updateDischargeFormat(dischargeFormat);
            if (updateDischargeFormat != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Discharge Format Updated Successfully", updateDischargeFormat), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Discharge Format Not Updated", new DischargeFormat()), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Discharge Format Name Already Exists", updateDischargeFormat), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXCEPTION:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateDischargeFormat), HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<GenericResponse<DischargeFormat>> deleteDischargeFormat(@RequestParam Long discFmtId){
        DischargeFormat deleteDischargeFormat;
        try{
            deleteDischargeFormat= dischargeFormatService.deleteDischargeFormatById(discFmtId);
            if (deleteDischargeFormat != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "DischargeFormat Deleted Successfully", deleteDischargeFormat), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Technical Error", new DischargeFormat()), HttpStatus.OK);
            }
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(), true, "Discharge Format With that Id Doesn't Exists", new DischargeFormat()), HttpStatus.OK);
        }
    }
}
