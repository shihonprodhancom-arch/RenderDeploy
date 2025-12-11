package com.example.HotelManagment.Service;





import com.example.HotelManagment.Entity.RoomEntity;

import java.util.List;

public interface RoomServiceInterface {

    List<RoomEntity> getAllRooms();

    RoomEntity getRoomById(Long id);

    RoomEntity createRoom(RoomEntity room);

    RoomEntity updateRoom(Long id, RoomEntity room);

    void deleteRoom(Long id);
}



