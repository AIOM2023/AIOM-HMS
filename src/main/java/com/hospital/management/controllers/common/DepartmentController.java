package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.Department;
import com.hospital.management.entities.search.DepartmentSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GenericResponse<DepartmentSearchResult>> departmentList(@RequestParam(name = "search") String search,
                                                                                  @RequestParam(defaultValue = "0") int pageNo,
                                                                                  @RequestParam(defaultValue = "50") int pageSize,
                                                                                  @RequestParam(name = "sortBy") String sortBy,
                                                                                  @RequestParam(defaultValue = "ASC") String sortOrder) {
        try {
            DepartmentSearchResult departmentSearchResult = departmentService.departmentList(search, pageNo, pageSize, sortBy, sortOrder);
            if (!departmentSearchResult.getData().isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Department  Records found", departmentSearchResult), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Department Records not found", departmentSearchResult), HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            DepartmentSearchResult departmentSearchResult = new DepartmentSearchResult();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", departmentSearchResult), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/departmentId")
    public ResponseEntity<GenericResponse<List<Department>>> findDepartmentById(@RequestParam(required = false) Long departmentId){
        log.info("System Parameters List By Id");

        List<Department> departmentList = new ArrayList<>();
        try {
            departmentList = departmentService.findDepartmentById(departmentId);
            if (!departmentList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Department List By Id", departmentList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Department List with this Id", departmentList), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No Department found with the given Id", departmentList), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", departmentList), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<Department>>> getAllSystemRequestList(){
        try {
            List<Department> departmentList = departmentService.departmentListAll();

            if (!departmentList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Department Records found", departmentList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "Department Records not found", departmentList), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<Department> departmentList = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", departmentList), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<Department>> saveDepartment(@RequestBody @Validated Department department){
        Department saveDepartment= new Department();
        try {
            saveDepartment = departmentService.saveDepartment(department);
            if (saveDepartment != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "Department Created Successfully", saveDepartment), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Department Not Created", new Department()), HttpStatus.OK);
            }

        }catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Department Name Already Exists", saveDepartment), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveDepartment), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<GenericResponse<Department>> updateDepartment(@RequestBody @Validated Department department){

        Department updateDepartment= new Department();
        try {
            updateDepartment = departmentService.updateDepartment(department);
            if (updateDepartment != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Department Updated Successfully", updateDepartment), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Department Not Updated", new Department()), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "Department Name Already Exists", updateDepartment), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println("EXCEPTION:" + ex.getMessage());
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateDepartment), HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<GenericResponse<Department>> deleteDepartment(@RequestParam Long departmentId){
        Department deleteDepartment= new Department();
        try{
            deleteDepartment= departmentService.delete(departmentId);
            if (deleteDepartment != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "Department Deleted Successfully", deleteDepartment), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Technical Error", new Department()), HttpStatus.OK);
            }
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NOT_FOUND.value(), true, "Department With that Id Doesn't Exists", new Department()), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", deleteDepartment), HttpStatus.OK);
        }
    }

}
