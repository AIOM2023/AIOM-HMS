package com.hospital.management.controllers.common;

import com.hospital.management.entities.commom.RoomGroup;
import com.hospital.management.entities.response.RoomGroupSearchResult;
import com.hospital.management.service.RoomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomGroup")
public class RoomGroupController {

    @Autowired
    RoomGroupService roomGroupService;

    @GetMapping
    public ResponseEntity<RoomGroupSearchResult> getAllRoomGroups( @RequestParam(name="search") String search,
                                                                   @RequestParam(defaultValue = "0") int pageNo,
                                                                   @RequestParam(defaultValue = "50") int pageSize,
                                                                   @RequestParam(name="sortBy") String sortBy,
                                                                   @RequestParam(defaultValue = "ASC") String sortOrder ) {
        RoomGroupSearchResult roomGroupSearchResult = roomGroupService.getAllRoomGroups(search, pageNo, pageSize, sortBy, sortOrder);
        return  ResponseEntity.ok(roomGroupSearchResult);
    }

    @GetMapping("/{roomGroupId}")
    public ResponseEntity<RoomGroup> getRoomGroupById(@PathVariable("roomGroupId") Long roomGroupId) {
        RoomGroup roomGroup = roomGroupService.findRoomGroupById(roomGroupId);
        return ResponseEntity.ok(roomGroup);

    }

    @PostMapping("/save")
    public ResponseEntity<RoomGroup> saveRoomGroup(@RequestBody @Validated RoomGroup roomGroup){
        RoomGroup createdRoomGroup = roomGroupService.saveRoomGroup(roomGroup);
        return new ResponseEntity<>(createdRoomGroup, HttpStatus.OK);
    }


    @PutMapping("/update/{roomGroupId}")
    public ResponseEntity<RoomGroup> updateRoomGroup(@RequestBody @Validated RoomGroup roomGroup, @PathVariable ("roomGroupId") Long roomGroupId){
        RoomGroup updatedRoomGroup = roomGroupService.updateRoomGroup(roomGroup, roomGroupId);
        return new ResponseEntity<>(updatedRoomGroup, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{roomGroupId}")
    public ResponseEntity<String> deleteRoomGroupById(@PathVariable("roomGroupId") Long roomGroupId){
        String successMsg = roomGroupService.deleteRoomGroupById(roomGroupId);
        return new ResponseEntity<>(successMsg, HttpStatus.OK);
    }

    @GetMapping("/codes")
    public ResponseEntity<List<String>> getAllRoomGroupCodes() {
        return  ResponseEntity.ok(roomGroupService.getAllRoomGroupCodes());
    }

}
