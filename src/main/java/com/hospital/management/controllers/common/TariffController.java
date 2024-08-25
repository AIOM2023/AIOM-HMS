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
    public ResponseEntity<List<Tariff>> tariffList() {
        List<Tariff> tariffList = tariffService.tariffList();
        return  ResponseEntity.ok(tariffList);

    }

    @GetMapping("/{tariffId}")
    public ResponseEntity<Tariff> getTariffById(@PathVariable("tariffId") Long tariffId) {
        Tariff tariff = tariffService.findTariffById(tariffId);
        return ResponseEntity.ok(tariff);

    }

    @PostMapping("/save")
    public ResponseEntity<Tariff> saveTariff(@RequestBody @Validated Tariff tariff){
        Tariff savedTariff = tariffService.save(tariff);
        return new ResponseEntity<>(savedTariff, HttpStatus.OK);
    }

    @PutMapping("/update/{tariffId}")
    public ResponseEntity<Tariff> updateTariffById(@RequestBody @Validated Tariff tariff, @PathVariable("tariffId") Long tariffId){
        Tariff updatedTariff = tariffService.update(tariff, tariffId);
        return new ResponseEntity<>(updatedTariff, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{tariffId}")
    public ResponseEntity<String> deleteTariffById(@PathVariable("tariffId") Long tariffId){
        String successMsg = tariffService.deleteTariffById(tariffId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
