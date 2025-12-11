//package com.example.HotelManagment.Repository;
//
//import com.example.HotelManagment.Entity.BookingEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
//}


package com.example.HotelManagment.Repository;

import com.example.HotelManagment.Entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByRoomNumber(String roomNumber);
}
