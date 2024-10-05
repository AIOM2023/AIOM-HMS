package com.hospital.management.controllers;

import com.hospital.management.entities.District;
import com.hospital.management.entities.response.DistrictNameId;
import com.hospital.management.entities.response.DistrictSearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<GenericResponse<DistrictSearchResult>> getAllDistricts(@RequestParam(name="search") String search,
                                                                      @RequestParam(defaultValue = "0") int pageNo,
                                                                      @RequestParam(defaultValue = "50") int pageSize,
                                                                      @RequestParam(name="sortBy") String sortBy,
                                                                      @RequestParam(defaultValue = "DESC") String sortOrder) {

        try{
           // List<District> districts = districtService.getAllDistricts();
            DistrictSearchResult districtSearchResult = districtService.getAllDistricts(search, pageNo, pageSize, sortBy, sortOrder);
            if(!districtSearchResult.getData().isEmpty()){
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(),true ,"Country Records found", districtSearchResult), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(),true, "Country Records not found", districtSearchResult), HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            DistrictSearchResult districtSearchResultList = new DistrictSearchResult();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "Something went wrong",districtSearchResultList),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{districtId}")
    public ResponseEntity<District> findDistrictById(@PathVariable("districtId") Long districtId) {
        District district = districtService.findDistrictById(districtId);
        return ResponseEntity.ok(district);
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<District>> saveDistrict(@RequestBody @Validated District district){
        District saveDistrict= new District();
        try{
            saveDistrict = districtService.saveDistrict(district);

        if (saveDistrict != null) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "District Created Successfully", saveDistrict), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "District Not Created", saveDistrict), HttpStatus.OK);
        }
    } catch (
    DuplicateEntryException exception) {
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "District Name Already Exists", district), HttpStatus.OK);
    } catch (Exception ex) {
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveDistrict), HttpStatus.OK);

    }
}


    @PutMapping("/update/{districtId}")
    public ResponseEntity<GenericResponse<District>> updateDistrict(@RequestBody @Validated District district, @PathVariable("districtId") Long districtId){
        District updateDistrict = new District();
        try{
        updateDistrict = districtService.updateDistrict(district, districtId);
        if (updateDistrict != null) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "District Updated Successfully", updateDistrict), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "District Not Updated", updateDistrict), HttpStatus.OK);
        }
    } catch (DuplicateEntryException exception) {
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "District Name Already Exists", district), HttpStatus.OK);
    } catch (Exception ex) {
        System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateDistrict), HttpStatus.OK);
    }
}


@DeleteMapping("/delete/{districtId}")
    public ResponseEntity<String> deleteDistrictById(@PathVariable("districtId") Long districtId){
        return new ResponseEntity<>(districtService.deleteDistrictById(districtId), HttpStatus.OK);
    }

    @GetMapping("/names/{stateId}")
    public ResponseEntity<List<DistrictNameId>> getAllDistrictNamesByState(@PathVariable("stateId") Long stateId){
        return new ResponseEntity<>(districtService.getAllDistrictNames(stateId), HttpStatus.OK);
    }
}
