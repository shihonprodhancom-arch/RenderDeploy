package com.example.HotelManagment.Service.NewService;

import com.example.HotelManagment.Dto.RoomGroupDTO;
import com.example.HotelManagment.Entity.newEntitys.Room;
import com.example.HotelManagment.Entity.newEntitys.RoomGroup;
import com.example.HotelManagment.Repository.RoomTypeRepository.RoomGroupRepository;
import com.example.HotelManagment.Repository.RoomTypeRepository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomGroupService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomGroupRepository roomGroupRepository;

    public List<RoomGroupDTO> getAllRoomGroups() {
        return roomGroupRepository.findAll().stream()
                .map(RoomGroupDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public RoomGroupDTO getRoomGroupById(Long id) {
        return roomGroupRepository.findById(id)
                .map(RoomGroupDTO::fromEntity)
                .orElse(null);
    }

    public RoomGroupDTO getRoomGroupByType(String type) {
        return roomGroupRepository.findByTypeIgnoreCase(type)
                .map(RoomGroupDTO::fromEntity)
                .orElse(null);
    }

    public RoomGroupDTO createRoomGroup(RoomGroupDTO roomGroupDTO) {
        RoomGroup roomGroup = new RoomGroup(roomGroupDTO.getType());
        RoomGroup saved = roomGroupRepository.save(roomGroup);
        return RoomGroupDTO.fromEntity(saved);
    }

    // ✅ Add Room to Group
    @Transactional
    public RoomGroupDTO addRoomToGroup(Long groupId, Room room) {
        RoomGroup roomGroup = roomGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Room group not found with id: " + groupId));

        if (roomRepository.existsByNumber(room.getNumber())) {
            throw new RuntimeException("Room number already exists: " + room.getNumber());
        }

        roomGroup.addRoom(room);
        roomGroupRepository.save(roomGroup);

        return RoomGroupDTO.fromEntity(roomGroup);
    }

    // ✅ Remove Room from Group
    @Transactional
    public RoomGroupDTO removeRoomFromGroup(Long groupId, Long roomId) {
        RoomGroup roomGroup = roomGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Room group not found with id: " + groupId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

        if (!room.getRoomGroup().getId().equals(groupId)) {
            throw new RuntimeException("Room does not belong to this group");
        }

        roomGroup.removeRoom(room);
        roomRepository.delete(room);
        roomGroupRepository.save(roomGroup);

        return RoomGroupDTO.fromEntity(roomGroup);
    }

    public RoomGroupDTO updateRoomGroup(Long id, RoomGroupDTO roomGroupDTO) {
        // Check if room group exists
        RoomGroup existingRoomGroup = roomGroupRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("RoomGroup not found with id: " + id));

        // Update the fields
        if (roomGroupDTO.getType() != null && !roomGroupDTO.getType().trim().isEmpty()) {
            // Check if type already exists (excluding current room group)
            if (roomGroupRepository.existsByTypeAndIdNot(roomGroupDTO.getType(), id)) {
                throw new IllegalArgumentException("Room group type already exists: " + roomGroupDTO.getType());
            }
            existingRoomGroup.setType(roomGroupDTO.getType());
        }

        // Save the updated room group
        RoomGroup updatedRoomGroup = roomGroupRepository.save(existingRoomGroup);

        // Convert to DTO and return
        return RoomGroupDTO.fromEntity(updatedRoomGroup);
    }
}
