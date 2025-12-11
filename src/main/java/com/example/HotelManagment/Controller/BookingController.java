//package com.example.HotelManagment.Controller;
//
//
//import com.example.HotelManagment.Entity.BookingEntity;
//
//import com.example.HotelManagment.Service.BookingService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/bookings")
//@CrossOrigin(origins = "http://localhost:4200")
//public class BookingController {
//
//    private final BookingService bookingService;
//
//    public BookingController(BookingService bookingService) {
//        this.bookingService = bookingService;
//    }
//
//    // Get all bookings
//    @GetMapping
//    public List<BookingEntity> getAllBookings() {
//        return bookingService.getAll();
//    }
//
//    // Add new booking
//    @PostMapping
//    public BookingEntity addBooking(@RequestBody BookingEntity booking) {
//        return bookingService.save(booking);
//    }
//
//    // Delete booking by ID
//    @DeleteMapping("/{id}")
//    public void deleteBooking(@PathVariable Long id) {
//        bookingService.delete(id);
//    }
//
//    // Optional: Get booking by ID
//    @GetMapping("/{id}")
//    public BookingEntity getBookingById(@PathVariable Long id) {
//        return bookingService.getById(id);
//    }
//
//    // Optional: Update booking
//    @PutMapping("/{id}")
//    public BookingEntity updateBooking(@PathVariable Long id, @RequestBody BookingEntity booking) {
//        return bookingService.update(id, booking);
//    }
//}


package com.example.HotelManagment.Controller;

import com.example.HotelManagment.Entity.BookingEntity;
import com.example.HotelManagment.Service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:4200")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingEntity> getAllBookings() {
        return bookingService.getAll();
    }

    @PostMapping
    public BookingEntity addBooking(@RequestBody BookingEntity booking) {
        return bookingService.save(booking);
    }

    @PutMapping("/{id}")
    public BookingEntity updateBooking(@PathVariable Long id, @RequestBody BookingEntity booking) {
        return bookingService.update(id, booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.delete(id);
    }

    @GetMapping("/{id}")
    public BookingEntity getBookingById(@PathVariable Long id) {
        return bookingService.getById(id);
    }

    @GetMapping("/room/{roomNumber}")
    public List<BookingEntity> getBookingsByRoom(@PathVariable String roomNumber) {
        return bookingService.findByRoomNumber(roomNumber);
    }
}
