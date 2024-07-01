
package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.BedDetails;
import com.hospital.management.service.BedDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bed/details")
public class BedDetailsController {


@Autowired
    BedDetailsService bedDetailsService;


@GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<BedDetails>> bedDetailsList() {
        List<BedDetails> bedDetailsList = bedDetailsService.bedDetailsList();
        return  ResponseEntity.ok(bedDetailsList);

    }


@CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<BedDetails> saveInsuranceComp(@RequestBody @Validated BedDetails bedDetails){
        bedDetailsService.save(bedDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }



@CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<BedDetails> updateAuthorization(@RequestBody @Validated BedDetails bedDetails){
        bedDetailsService.update(bedDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
