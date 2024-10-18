package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.SystemParameters;
import com.hospital.management.entities.search.SystemParametersSearchList;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.SystemParametersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/system/parameters")
public class SystemParametersController {
    private static final Logger logger = LoggerFactory.getLogger(SystemParametersController.class);

    @Autowired
    private SystemParametersService systemParametersService;

    @GetMapping
    public ResponseEntity<GenericResponse<SystemParametersSearchList>> systemParamsList(
            @RequestParam(name="search", required = false) String search,
            @RequestParam(defaultValue = "0",required = false) int pageNo,
            @RequestParam(defaultValue = "50",required = false) int pageSize,
            @RequestParam(name="sortBy",required = false) String sortBy,
            @RequestParam(defaultValue = "DESC",required = false) String sortOrder) {
        try{
            SystemParametersSearchList systemParametersSearchList = systemParametersService.getSystemParametersList(search, pageNo, pageSize, sortBy, sortOrder);

            if(!systemParametersSearchList.getData().isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(),true ,"System Parameters Records found", systemParametersSearchList), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(),true, "System Parameters Records not found", systemParametersSearchList), HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            SystemParametersSearchList systemParametersSearchList = new SystemParametersSearchList();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "Something went wrong",systemParametersSearchList),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/sysParamByMainId")
    public ResponseEntity<GenericResponse<List<SystemParameters>>> systemParamsMainListById(@RequestParam(required = false) List<Long> paramsMainId) {
        logger.info("System Parameters List By Main Id");

        List<SystemParameters> listSystemParamsMainId = new ArrayList<>();
        try {
            listSystemParamsMainId = systemParametersService.getSystemParametersListByMainId(paramsMainId);
            if (!listSystemParamsMainId.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters List By Main Id", listSystemParamsMainId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters List with this Main Id", listSystemParamsMainId), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters found with the given Main Id", listSystemParamsMainId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", listSystemParamsMainId), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sysParamById")
    public ResponseEntity<GenericResponse<List<SystemParameters>>> systemParamsListById(@RequestParam(required = false) Long paramId){
        logger.info("System Parameters List By Id");

        List<SystemParameters> systemParamsId = new ArrayList<>();
        try {
            systemParamsId = systemParametersService.getSystemParametersListById(paramId);
            if (!systemParamsId.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters List By Id", systemParamsId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters List with this Id", systemParamsId), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters found with the given Id", systemParamsId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", systemParamsId), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sysParamByIds")
    public ResponseEntity<GenericResponse<List<SystemParameters>>> systemParamsListByIds(@RequestParam(required = false) List<Long> paramIds){
        logger.info("System Parameters List By Ids");

        List<SystemParameters> systemParamsId = new ArrayList<>();
        try {
            systemParamsId = systemParametersService.getSystemParametersWithIds(paramIds);
            if (!systemParamsId.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters List By Id", systemParamsId), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters List with this Id", systemParamsId), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No System Parameters found with the given Id", systemParamsId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", systemParamsId), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<SystemParameters>> saveSystemParameters(@RequestBody @Validated SystemParameters systemParameters) {
        SystemParameters saveSystemParameters = new SystemParameters();

        try {
            saveSystemParameters = systemParametersService.save(systemParameters);

            if (saveSystemParameters != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "System Parameters Created Successfully", saveSystemParameters), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "System Parameters Not Created", saveSystemParameters), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "System Parameter Name Already Exists", systemParameters), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveSystemParameters), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<SystemParameters>> updateSystemParameters(@RequestBody @Validated SystemParameters systemParameters){

        SystemParameters updateSystemParameters= new SystemParameters();
        try {
            updateSystemParameters = systemParametersService.update(systemParameters);
            if (updateSystemParameters != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters Updated Successfully", updateSystemParameters), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "System Parameters Not Updated", updateSystemParameters), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "System Parameter Name Already Exists", systemParameters), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateSystemParameters), HttpStatus.OK);
        }
    }



    @PostMapping("/delete")
    public ResponseEntity<GenericResponse<SystemParameters>> deleteSystemParameters(@RequestParam Long paramId){
        SystemParameters deleteSystemParameters= new SystemParameters();
        try{
            deleteSystemParameters= systemParametersService.delete(paramId);
            if (deleteSystemParameters != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "System Parameters Deleted Successfully", new SystemParameters()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Technical Error", new SystemParameters()), HttpStatus.OK);
            }
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(), true, "System Parameters With that Id Doesn't Exists", new SystemParameters()), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", deleteSystemParameters), HttpStatus.OK);
        }
    }

}
