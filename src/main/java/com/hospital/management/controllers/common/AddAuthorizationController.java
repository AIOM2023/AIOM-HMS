package com.hospital.management.controllers.common;

import com.hospital.management.entities.Country;
import com.hospital.management.entities.commom.Authorization;
import com.hospital.management.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/add/authorization")
public class AddAuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<Authorization>> addAuthorizationList() {
        List<Authorization> authorization = authorizationService.getAuthorizationList();
        return  ResponseEntity.ok(authorization);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<Authorization> saveAuthorization(@RequestBody @Validated Authorization authorizationModel){
        authorizationService.save(authorizationModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update/{authId}")
    public ResponseEntity<Authorization> updateAuthorization(@RequestBody @Validated Authorization authorization){
        authorizationService.update(authorization);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
