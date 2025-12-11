package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.GuestEntity;
import java.util.List;

public interface GuestService {
    List<GuestEntity> getAll();
    GuestEntity getById(Long id);
    GuestEntity save(GuestEntity guest);
    GuestEntity update(Long id, GuestEntity guest);
    void delete(Long id);
}
