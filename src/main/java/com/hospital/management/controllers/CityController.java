package com.hospital.management.controllers;

import com.hospital.management.entities.City;
import com.hospital.management.entities.response.CityNameId;
import com.hospital.management.entities.response.CitySearchResult;
import com.hospital.management.exceptions.DuplicateEntryException;
import com.hospital.management.exceptions.ResourceNotFoundException;
import com.hospital.management.model.GenericResponse;
import com.hospital.management.service.CityService;
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
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<CitySearchResult>> getAllCities(@RequestParam(name="search", required = false) String search,
                                                                          @RequestParam(defaultValue = "0",required = false) int pageNo,
                                                                          @RequestParam(defaultValue = "50",required = false) int pageSize,
                                                                          @RequestParam(name="sortBy",required = false) String sortBy,
                                                                          @RequestParam(defaultValue = "DESC",required = false) String sortOrder) {
       try{
        CitySearchResult citySearchResult = cityService.getAllCities(search, pageNo, pageSize, sortBy, sortOrder);
        if(!citySearchResult.getData().isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(),true ,"City Records found", citySearchResult), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(),true, "City Records not found", citySearchResult), HttpStatus.NOT_FOUND);
        }
    }catch (Exception ex){
           CitySearchResult citySearchResultList = new CitySearchResult();
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(),false, "Something went wrong",citySearchResultList),HttpStatus.BAD_REQUEST);
    }
    }


    @GetMapping("/{cityId}")
   // @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<List<City>>> findCityById(@RequestParam(required = false) Long cityId) {
        log.info("City List By Id");

        List<City> cityList = new ArrayList<>();

        try {
            cityList = cityService.findCityById(cityId);
            if (!cityList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "City List By Id", cityList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No City List with this Id", cityList), HttpStatus.OK);
            }
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "No City found with the given Id", cityList), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "City went wrong", cityList), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<GenericResponse<List<City>>> getAllCityList(){
        try {
            List<City> cityList = cityService.cityListAll();

            if (!cityList.isEmpty()) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "City Records found", cityList), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.NO_CONTENT.value(), true, "City Records not found", cityList), HttpStatus.NOT_FOUND);
            }

        }catch (Exception ex) {
            List<City> cityEmptyList = new ArrayList<>();
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something went wrong", cityEmptyList), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<City>> saveCity(@RequestBody @Validated City city){
        City saveCity = new City();
        try {
        saveCity = cityService.saveCity(city);
            if (saveCity != null) {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CREATED.value(), true, "city Created Successfully", saveCity), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "city Not Created", saveCity), HttpStatus.OK);
            }
        } catch (DuplicateEntryException exception) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "city Name Already Exists", city), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", saveCity), HttpStatus.OK);
        }
    }

    @PutMapping("/update/{cityId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<GenericResponse<City>> updateCity(@RequestBody @Validated City city, @PathVariable("cityId") Long cityId){
     City updateCity = new City();
     try {
         updateCity = cityService.updateCity(city, cityId);
        if (updateCity != null) {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.OK.value(), true, "city Updated Successfully", updateCity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "city Not Updated", updateCity), HttpStatus.OK);
        }
    } catch (DuplicateEntryException exception) {
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.CONFLICT.value(), true, "city Name Already Exists", city), HttpStatus.OK);
    } catch (Exception ex) {
        System.out.println("EXXXXXXXXXXXXXXX:" + ex.getMessage());
        return new ResponseEntity<>(new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Something Wrong", updateCity), HttpStatus.OK);
    }
}

    @DeleteMapping("/delete/{cityId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteCityById(@PathVariable("cityId") Long cityId){
        return new ResponseEntity<>(cityService.deleteCityById(cityId), HttpStatus.OK);
    }

    @GetMapping("/names/{districtId}")
    public ResponseEntity<List<CityNameId>> getAllDistrictNamesByState(@PathVariable("districtId") Long districtId){
        return new ResponseEntity<>(cityService.getAllCityNames(districtId), HttpStatus.OK);
    }
    
}
