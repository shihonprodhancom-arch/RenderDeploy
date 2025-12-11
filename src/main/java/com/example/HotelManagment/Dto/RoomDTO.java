package com.example.HotelManagment.Dto;

import com.example.HotelManagment.Entity.newEntitys.Room;
import com.example.HotelManagment.Entity.newEntitys.RoomGroup;
import lombok.Data;

import java.util.List;

@Data
public class RoomDTO {
    private Long id;
    private Integer number;
    private Double price;
    private Integer capacity;
    private List<String> services;
    private String image; // ফাইল আপলোডে nullable হতে পারে
    private RoomGroupDTO roomGroup;
    private String type;
    private String status;

    // Constructors
    public RoomDTO() {}

    public RoomDTO(Integer number, Double price, Integer capacity, List<String> services, String image, RoomGroupDTO roomGroup) {
        this.number = number;
        this.price = price;
        this.capacity = capacity;
        this.services = services;
        this.image = image;
        this.roomGroup = roomGroup;
    }

    public RoomDTO(Integer number, Double price, Integer capacity, List<String> services, String image, RoomGroupDTO roomGroup, String type, String status) {
        this.number = number;
        this.price = price;
        this.capacity = capacity;
        this.services = services;
        this.image = image;
        this.roomGroup = roomGroup;
        this.type = type;
        this.status = status;
    }

    // Static factory method from Entity
    public static RoomDTO fromEntity(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setNumber(room.getNumber());
        dto.setPrice(room.getPrice());
        dto.setCapacity(room.getCapacity());
        dto.setServices(room.getServices());
        dto.setImage(room.getImage() != null ? room.getImage() : null); // image ফাইল না থাকলে null
        dto.setType(room.getType());
        dto.setStatus(room.getStatus());

        if(room.getRoomGroup() != null) {
            RoomGroupDTO roomGroupDTO = new RoomGroupDTO();
            roomGroupDTO.setId(room.getRoomGroup().getId());
            roomGroupDTO.setType(room.getRoomGroup().getType());
            dto.setRoomGroup(roomGroupDTO);
        }
        return dto;
    }

    // Convert DTO to Entity
    public static Room toEntity(RoomDTO roomDTO) {
        Room room = new Room();
        room.setId(roomDTO.getId());
        room.setNumber(roomDTO.getNumber());
        room.setPrice(roomDTO.getPrice());
        room.setCapacity(roomDTO.getCapacity());
        room.setServices(roomDTO.getServices());
        room.setImage(roomDTO.getImage());
        room.setType(roomDTO.getType());
        room.setStatus(roomDTO.getStatus() != null ? roomDTO.getStatus() : "Available");

        // Handle RoomGroup association
        if (roomDTO.getRoomGroup() != null && roomDTO.getRoomGroup().getId() != null) {
            RoomGroup roomGroup = new RoomGroup();
            roomGroup.setId(roomDTO.getRoomGroup().getId());
            room.setRoomGroup(roomGroup);
        }

        return room;
    }

    // Get type from roomGroup or direct type field
    public String getType() {
        if (this.type != null && !this.type.trim().isEmpty()) {
            return this.type;
        } else if (this.roomGroup != null && this.roomGroup.getType() != null) {
            return this.roomGroup.getType();
        }
        return null;
    }

    // Utility methods
    public boolean isAvailable() {
        return "Available".equalsIgnoreCase(this.status);
    }

    public boolean isBooked() {
        return "Booked".equalsIgnoreCase(this.status);
    }

    public boolean isUnderMaintenance() {
        return "Maintenance".equalsIgnoreCase(this.status);
    }

    // Validation method
    public boolean isValid() {
        return this.number != null &&
                this.number > 0 &&
                this.price != null &&
                this.price >= 0 &&
                this.capacity != null &&
                this.capacity > 0 &&
                (this.type != null && !this.type.trim().isEmpty()) &&
                this.roomGroup != null &&
                this.roomGroup.getId() != null;
    }

    // Builder pattern for fluent creation
    public static RoomDTOBuilder builder() {
        return new RoomDTOBuilder();
    }

    public static class RoomDTOBuilder {
        private Long id;
        private Integer number;
        private Double price;
        private Integer capacity;
        private List<String> services;
        private String image;
        private RoomGroupDTO roomGroup;
        private String type;
        private String status;

        public RoomDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RoomDTOBuilder number(Integer number) {
            this.number = number;
            return this;
        }

        public RoomDTOBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public RoomDTOBuilder capacity(Integer capacity) {
            this.capacity = capacity;
            return this;
        }

        public RoomDTOBuilder services(List<String> services) {
            this.services = services;
            return this;
        }

        public RoomDTOBuilder image(String image) {
            this.image = image;
            return this;
        }

        public RoomDTOBuilder roomGroup(RoomGroupDTO roomGroup) {
            this.roomGroup = roomGroup;
            return this;
        }

        public RoomDTOBuilder roomGroupId(Long roomGroupId) {
            if (this.roomGroup == null) {
                this.roomGroup = new RoomGroupDTO();
            }
            this.roomGroup.setId(roomGroupId);
            return this;
        }

        public RoomDTOBuilder type(String type) {
            this.type = type;
            return this;
        }

        public RoomDTOBuilder status(String status) {
            this.status = status;
            return this;
        }

        public RoomDTO build() {
            RoomDTO dto = new RoomDTO();
            dto.setId(this.id);
            dto.setNumber(this.number);
            dto.setPrice(this.price);
            dto.setCapacity(this.capacity);
            dto.setServices(this.services);
            dto.setImage(this.image);
            dto.setRoomGroup(this.roomGroup);
            dto.setType(this.type);
            dto.setStatus(this.status);
            return dto;
        }
    }
}