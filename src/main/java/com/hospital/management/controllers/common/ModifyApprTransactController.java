package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.ModifyApprTransact;
import com.hospital.management.service.ModifyApprTransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modify/appr/transact")
public class ModifyApprTransactController {

    @Autowired
    ModifyApprTransactService modifyApprTransactService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<List<ModifyApprTransact>> modifyApprTransactDataList() {
        List<ModifyApprTransact> modifyApprTransactList = modifyApprTransactService.modifyApprTransactList();
        return  ResponseEntity.ok(modifyApprTransactList);

    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/save")
    public ResponseEntity<ModifyApprTransact> saveModifyApprTransact(@RequestBody @Validated ModifyApprTransact modifyApprTransact){
        modifyApprTransactService.save(modifyApprTransact);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/update")
    public ResponseEntity<ModifyApprTransact> updateModifyApprTransact(@RequestBody @Validated ModifyApprTransact modifyApprTransact){
        modifyApprTransactService.save(modifyApprTransact);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
