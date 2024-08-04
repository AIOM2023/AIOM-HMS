package com.hospital.management.controllers.common;


import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tariff")
public class TariffController {

    @Autowired
    TariffService tariffService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Tariff>> tariffList() {
        List<Tariff> tariffList = tariffService.tariffList();
        return  ResponseEntity.ok(tariffList);

    }

    @GetMapping("/{tariffId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Tariff> getTariffById(@PathVariable("tariffId") Long tariffId) {
        Tariff tariff = tariffService.findTariffById(tariffId);
        return ResponseEntity.ok(tariff);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Tariff> saveTariff(@RequestBody @Validated Tariff tariff){
        Tariff savedTariff = tariffService.save(tariff);
        return new ResponseEntity<>(savedTariff, HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/update/{tariffId}")
    public ResponseEntity<Tariff> updateTariffById(@RequestBody @Validated Tariff tariff, @PathVariable("tariffId") Long tariffId){
        Tariff updatedTariff = tariffService.update(tariff, tariffId);
        return new ResponseEntity<>(updatedTariff, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{tariffId}")
    public ResponseEntity<String> deleteTariffById(@PathVariable("tariffId") Long tariffId){
        String successMsg = tariffService.deleteTariffById(tariffId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
