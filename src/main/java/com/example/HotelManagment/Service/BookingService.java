//package com.example.HotelManagment.Service;
//
//import com.example.HotelManagment.Entity.BookingEntity;
//
//import java.util.List;
//
//public interface BookingService {
//    List<BookingEntity> getAll();
//
//    BookingEntity getById(Long id);
//
//    BookingEntity save(BookingEntity booking);
//
//    BookingEntity update(Long id, BookingEntity booking);
//
//    void delete(Long id);
//}


package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.BookingEntity;
import java.util.List;

public interface BookingService {
    List<BookingEntity> getAll();
    BookingEntity getById(Long id);
    BookingEntity save(BookingEntity booking);
    BookingEntity update(Long id, BookingEntity booking);
    void delete(Long id);

    List<BookingEntity> findByRoomNumber(String roomNumber);
}
