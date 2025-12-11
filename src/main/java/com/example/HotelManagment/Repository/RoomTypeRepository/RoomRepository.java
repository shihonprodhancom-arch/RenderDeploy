//package com.example.HotelManagment.Repository.RoomTypeRepository;
//
//import com.example.HotelManagment.Entity.newEntitys.Room;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//import java.util.List;
//
//public interface RoomRepository extends JpaRepository<Room, Long> {
//
//    // ✅ এই তিনটা method যোগ করো
//    Optional<Room> findByNumber(Integer number);
//
//    boolean existsByNumber(Integer number);
//
//    // (optional) যদি search এর জন্য দরকার হয়
//    List<Room> findByPriceLessThanEqualAndCapacityGreaterThanEqual(Double price, Integer capacity);
//}


package com.example.HotelManagment.Repository.RoomTypeRepository;

import com.example.HotelManagment.Entity.newEntitys.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByNumber(Integer number);
    boolean existsByNumber(Integer number);
}
