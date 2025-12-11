package com.example.HotelManagment.Entity.newEntitys;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class RoomGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "roomGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    public RoomGroup() {}

    public RoomGroup(String type) {
        this.type = type;
    }

    // ✅ Helper Methods
    public void addRoom(Room room) {
        rooms.add(room);
        room.setRoomGroup(this);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setRoomGroup(null);
    }
}
