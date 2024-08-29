package com.hospital.management.controllers;

import com.hospital.management.entities.City;
import com.hospital.management.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{cityId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<City> findCityById(@PathVariable("cityId") Long cityId) {
        City city = cityService.findCityById(cityId);
        return ResponseEntity.ok(city);
    }

    @PostMapping("/save")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<City> saveCity(@RequestBody @Validated City city){
        return new ResponseEntity<>(cityService.saveCity(city), HttpStatus.CREATED);
    }

    @PutMapping("/update/{cityId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<City> updateCity(@RequestBody @Validated City city, @PathVariable("cityId") Long cityId){
        return new ResponseEntity<>(cityService.updateCity(city, cityId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cityId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> deleteCityById(@PathVariable("cityId") Long cityId){
        return new ResponseEntity<>(cityService.deleteCityById(cityId), HttpStatus.OK);
    }

    @GetMapping("/names/{districtName}")
    public ResponseEntity<List<String>> getAllCityNamesByDistrict(@PathVariable("districtName") String districtName){
        return new ResponseEntity<>(cityService.getAllCityNames(districtName), HttpStatus.OK);
    }
    
}
