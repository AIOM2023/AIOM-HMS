package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.HowDid;
import com.hospital.management.entities.commom.InsuranceComp;
import com.hospital.management.entities.commom.Tariff;
import com.hospital.management.service.HowDidService;
import com.hospital.management.service.InsuranceCompService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/how/did")
public class HowDidController {

    @Autowired
    HowDidService howDidService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<HowDid>> systemParamsList() {
        List<HowDid> howDidList = howDidService.howDidList();
        return ResponseEntity.ok(howDidList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<HowDid> saveHowDid(@RequestBody @Validated HowDid howDid) {
        howDidService.saveHowDid(howDid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{howDidId}")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<HowDid> getHowDidById(@PathVariable("howDidId") Integer howDidId) {
        HowDid howDid = howDidService.findHowDidByHowDidId(howDidId);
        return ResponseEntity.ok(howDid);

    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping ("/update/{howDidId}")
    public ResponseEntity<HowDid> updateHowDid(@RequestBody @Validated HowDid howDid, @PathVariable("howDidId") Integer howDidId) {
        howDidService.updateHowDid(howDid, howDidId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{howDidId}")
    public ResponseEntity<String> deleteHowDidById(@PathVariable("howDidId") Integer howDidId) {
        String successMsg = howDidService.deleteHowDidByHowDidId(howDidId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }
}
