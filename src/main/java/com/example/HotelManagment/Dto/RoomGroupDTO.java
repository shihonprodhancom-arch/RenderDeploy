package com.example.HotelManagment.Dto;

import com.example.HotelManagment.Entity.newEntitys.RoomGroup;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoomGroupDTO {
    private Long id;
    private String type;
    private List<RoomDTO> rooms;

    // ✅ Default constructor
    public RoomGroupDTO() {}

    // ✅ Parameterized constructors
    public RoomGroupDTO(String type, List<RoomDTO> rooms) {
        this.type = type;
        this.rooms = rooms;
    }

    public RoomGroupDTO(Long id) {
        this.id = id;
    }

    // ✅ Convert Entity → DTO
    public static RoomGroupDTO fromEntity(RoomGroup roomGroup) {
        if (roomGroup == null) return null;

        RoomGroupDTO dto = new RoomGroupDTO();
        dto.setId(roomGroup.getId());
        dto.setType(roomGroup.getType());

        // Prevent NullPointerException if rooms list is null
        if (roomGroup.getRooms() != null) {
            dto.setRooms(roomGroup.getRooms().stream()
                    .map(RoomDTO::fromEntity)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
