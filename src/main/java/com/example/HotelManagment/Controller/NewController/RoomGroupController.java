package com.example.HotelManagment.Controller.NewController;


import com.example.HotelManagment.Dto.RoomGroupDTO;
import com.example.HotelManagment.Service.NewService.RoomGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/room-groups")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomGroupController {

    @Autowired
    private RoomGroupService roomGroupService;

    @GetMapping
    public ResponseEntity<List<RoomGroupDTO>> getAllRoomGroups() {
        List<RoomGroupDTO> roomGroups = roomGroupService.getAllRoomGroups();
        return ResponseEntity.ok(roomGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomGroupDTO> getRoomGroupById(@PathVariable Long id) {
        RoomGroupDTO roomGroup = roomGroupService.getRoomGroupById(id);
        if (roomGroup != null) {
            return ResponseEntity.ok(roomGroup);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<RoomGroupDTO> getRoomGroupByType(@PathVariable String type) {
        RoomGroupDTO roomGroup = roomGroupService.getRoomGroupByType(type);
        if (roomGroup != null) {
            return ResponseEntity.ok(roomGroup);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<RoomGroupDTO> createRoomGroup(@RequestBody RoomGroupDTO roomGroupDTO) {
        RoomGroupDTO created = roomGroupService.createRoomGroup(roomGroupDTO);
        return ResponseEntity.ok(created);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<RoomGroupDTO> updateRoomGroup(@PathVariable Long id, @RequestBody RoomGroupDTO roomGroupDTO) {
//
//        RoomGroupDTO created = roomGroupService.updateRoomGroup(id, roomGroupDTO);
//        return ResponseEntity.ok(created);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoomGroup(@PathVariable Long id, @RequestBody RoomGroupDTO roomGroupDTO) {
        try {
            RoomGroupDTO updatedRoomGroup = roomGroupService.updateRoomGroup(id, roomGroupDTO);
            return ResponseEntity.ok(updatedRoomGroup);
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", e.getMessage())
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of("error", e.getMessage())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    Map.of("error", "Failed to update room group")
            );
        }
    }
}