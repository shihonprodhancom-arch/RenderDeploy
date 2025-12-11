package com.example.HotelManagment.Service.NewService;

import com.example.HotelManagment.Dto.RoomDTO;
import com.example.HotelManagment.Dto.RoomGroupDTO;
import com.example.HotelManagment.Entity.newEntitys.Room;
import com.example.HotelManagment.Entity.newEntitys.RoomGroup;

import com.example.HotelManagment.Repository.RoomTypeRepository.RoomGroupRepository;
import com.example.HotelManagment.Repository.RoomTypeRepository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomGroupRepository roomGroupRepository;

    // ✅ Get all rooms
    public List<RoomDTO> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomDTO::fromEntity)
                .collect(Collectors.toList());
    }

    // ✅ Get single room
    public RoomDTO getRoomById(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        return RoomDTO.fromEntity(room);
    }

    // ✅ Create room
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Room room = RoomDTO.toEntity(roomDTO);

        // 🔹 Link to RoomGroup (based on type)
        RoomGroup group = roomGroupRepository.findById(roomDTO.getRoomGroup().getId())
                .orElseGet(() -> {
                    RoomGroup newGroup = new RoomGroup();
                    newGroup.setType(roomDTO.getType());
                    return roomGroupRepository.save(newGroup);
                });

        room.setRoomGroup(group);
        Room savedRoom = roomRepository.save(room);
        return RoomDTO.fromEntity(savedRoom);
    }

    // ✅ Update room
    public RoomDTO updateRoom(Long id, RoomDTO roomDTO) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

        existingRoom.setNumber(roomDTO.getNumber());
        existingRoom.setType(roomDTO.getType());
        existingRoom.setStatus(roomDTO.getStatus());
        existingRoom.setPrice(roomDTO.getPrice());
        existingRoom.setImage(roomDTO.getImage());

        // 🔹 Update RoomGroup
        RoomGroup group = roomGroupRepository.findById(roomDTO.getRoomGroup().getId())
                .orElseGet(() -> {
                    RoomGroup newGroup = new RoomGroup();
                    newGroup.setType(roomDTO.getType());
                    return roomGroupRepository.save(newGroup);
                });

        existingRoom.setRoomGroup(group);

        Room updatedRoom = roomRepository.save(existingRoom);
        return RoomDTO.fromEntity(updatedRoom);
    }

    // ✅ Delete room
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }

    // ✅ Get all groups (Single, Double, Family, etc.)
    public List<RoomGroupDTO> getAllRoomGroups() {
        List<RoomGroup> groups = roomGroupRepository.findAll();
        return groups.stream()
                .map(RoomGroupDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
