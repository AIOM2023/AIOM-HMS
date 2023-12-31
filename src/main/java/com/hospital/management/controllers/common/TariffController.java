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

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Tariff> saveInsuranceComp(@RequestBody @Validated Tariff tariff){
        tariffService.save(tariff);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<Tariff> updateAuthorization(@RequestBody @Validated Tariff tariff){
        tariffService.update(tariff);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
