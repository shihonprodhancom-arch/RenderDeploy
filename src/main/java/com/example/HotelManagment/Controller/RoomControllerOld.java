package com.example.HotelManagment.Controller;



import com.example.HotelManagment.Entity.RoomEntity;
import com.example.HotelManagment.Service.RoomServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms/old")
@CrossOrigin(origins = "http://localhost:4200")  // Angular default port
public class RoomControllerOld {

    private final RoomServiceInterface roomServiceInterface;

    public RoomControllerOld(RoomServiceInterface roomServiceInterface) {
        this.roomServiceInterface = roomServiceInterface;
    }

    @GetMapping
    public List<RoomEntity> getAllRooms() {
        return roomServiceInterface.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomEntity getRoomById(@PathVariable Long id) {
        return roomServiceInterface.getRoomById(id);
    }

    @PostMapping
    public RoomEntity createRoom(@RequestBody RoomEntity room) {
        return roomServiceInterface.createRoom(room);
    }

    @PutMapping("/{id}")
    public RoomEntity updateRoom(@PathVariable Long id, @RequestBody RoomEntity room) {
        return roomServiceInterface.updateRoom(id, room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomServiceInterface.deleteRoom(id);
    }
}


