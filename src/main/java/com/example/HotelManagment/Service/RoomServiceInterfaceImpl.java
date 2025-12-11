package com.example.HotelManagment.Service;



import com.example.HotelManagment.Entity.RoomEntity;
import com.example.HotelManagment.Repository.RoomRepositoryOld;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceInterfaceImpl implements RoomServiceInterface {

    private final RoomRepositoryOld roomRepositoryOld;

    public RoomServiceInterfaceImpl(RoomRepositoryOld roomRepositoryOld) {
        this.roomRepositoryOld = roomRepositoryOld;
    }

    @Override
    public List<RoomEntity> getAllRooms() {
        return roomRepositoryOld.findAll();
    }

    @Override
    public RoomEntity getRoomById(Long id) {
        return roomRepositoryOld.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public RoomEntity createRoom(RoomEntity room) {
        return roomRepositoryOld.save(room);
    }

    @Override
    public RoomEntity updateRoom(Long id, RoomEntity room) {
        RoomEntity existingRoom = roomRepositoryOld.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        existingRoom.setNumber(room.getNumber());
        existingRoom.setType(room.getType());
        existingRoom.setStatus(room.getStatus());
        existingRoom.setPrice(room.getPrice());
        return roomRepositoryOld.save(existingRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepositoryOld.deleteById(id);
    }
}

