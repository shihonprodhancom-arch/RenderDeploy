//package com.example.HotelManagment.Service;
//
//import com.example.HotelManagment.Entity.BookingEntity;
//import com.example.HotelManagment.Entity.newEntitys.Room;
//import com.example.HotelManagment.Repository.BookingRepository;
//import com.example.HotelManagment.Repository.RoomTypeRepository.RoomRepository;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    private final BookingRepository bookingRepository;
//    private final RoomRepository roomRepository;
//
//    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
//        this.bookingRepository = bookingRepository;
//        this.roomRepository = roomRepository;
//    }
//
//    @Override
//    public List<BookingEntity> getAll() {
//        return bookingRepository.findAll();
//    }
//
//    @Override
//    public BookingEntity getById(Long id) {
//        return bookingRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public BookingEntity save(BookingEntity booking) {
//        if (booking.getStatus() == null) booking.setStatus("Pending");
//        if (booking.getPaymentMethod() == null) booking.setPaymentMethod("");
//        if (booking.getPaymentInfo() == null) booking.setPaymentInfo("");
//
//        // ✅ Check if room is available
//        Optional<Room> roomOpt = roomRepository.findByNumber(Integer.parseInt(booking.getRoomNumber()));
//        if (roomOpt.isEmpty()) {
//            throw new RuntimeException("Room not found!");
//        }
//
//        Room room = roomOpt.get();
//
//        if (!room.getStatus().equalsIgnoreCase("Available")) {
//            throw new RuntimeException("Room already booked!");
//        }
//
//        // ✅ Mark room as booked
//        room.setStatus("Booked");
//        roomRepository.save(room);
//
//        return bookingRepository.save(booking);
//    }
//
//    @Override
//    public BookingEntity update(Long id, BookingEntity booking) {
//        return bookingRepository.findById(id).map(existingBooking -> {
//            existingBooking.setGuestName(booking.getGuestName());
//            existingBooking.setRoomNumber(booking.getRoomNumber());
//            existingBooking.setCheckInDate(booking.getCheckInDate());
//            existingBooking.setCheckOutDate(booking.getCheckOutDate());
//            existingBooking.setTotalPrice(booking.getTotalPrice());
//            existingBooking.setStatus(booking.getStatus() != null ? booking.getStatus() : existingBooking.getStatus());
//            existingBooking.setPaymentMethod(booking.getPaymentMethod() != null ? booking.getPaymentMethod() : existingBooking.getPaymentMethod());
//            existingBooking.setPaymentInfo(booking.getPaymentInfo() != null ? booking.getPaymentInfo() : existingBooking.getPaymentInfo());
//
//            // ✅ যদি checkout তারিখ পার হয়ে যায়, তাহলে room আবার available করবো
//            Optional<Room> roomOpt = roomRepository.findByNumber(Integer.parseInt(existingBooking.getRoomNumber()));
//            roomOpt.ifPresent(room -> {
//                if (existingBooking.getCheckOutDate() != null &&
//                        existingBooking.getCheckOutDate().isBefore(LocalDate.now())) {
//                    room.setStatus("Available");
//                    roomRepository.save(room);
//                }
//            });
//
//            return bookingRepository.save(existingBooking);
//        }).orElse(null);
//    }
//
//    @Override
//    public void delete(Long id) {
//        bookingRepository.findById(id).ifPresent(booking -> {
//            // ✅ Deleting booking → make room available again
//            roomRepository.findByNumber(Integer.parseInt(booking.getRoomNumber()))
//                    .ifPresent(room -> {
//                        room.setStatus("Available");
//                        roomRepository.save(room);
//                    });
//        });
//        bookingRepository.deleteById(id);
//    }
//}


package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.BookingEntity;
import com.example.HotelManagment.Entity.newEntitys.Room;
import com.example.HotelManagment.Repository.BookingRepository;
import com.example.HotelManagment.Repository.RoomTypeRepository.RoomRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<BookingEntity> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public BookingEntity getById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public BookingEntity save(BookingEntity booking) {
        // রুম availability check
        Room room = roomRepository.findByNumber(Integer.parseInt(booking.getRoomNumber())).orElse(null);
        if (room != null && room.getStatus().equals("Booked")) {
            throw new RuntimeException("Room is already booked!");
        }

        // Booking save হলে রুম status update করা হবে
        if (room != null) {
            room.setStatus("Booked");
            roomRepository.save(room);
        }

        if (booking.getStatus() == null) booking.setStatus("Pending");
        if (booking.getPaymentMethod() == null) booking.setPaymentMethod("");
        if (booking.getPaymentInfo() == null) booking.setPaymentInfo("");

        return bookingRepository.save(booking);
    }

    @Override
    public BookingEntity update(Long id, BookingEntity booking) {
        return bookingRepository.findById(id).map(existingBooking -> {

            existingBooking.setGuestName(booking.getGuestName());
            existingBooking.setRoomNumber(booking.getRoomNumber());
            existingBooking.setCheckInDate(booking.getCheckInDate());
            existingBooking.setCheckOutDate(booking.getCheckOutDate());
            existingBooking.setTotalPrice(booking.getTotalPrice());
            existingBooking.setStatus(booking.getStatus());
            existingBooking.setPaymentMethod(booking.getPaymentMethod());
            existingBooking.setPaymentInfo(booking.getPaymentInfo());

            // Checkout হলে রুম আবার Available করে দাও
            if (booking.getStatus().equalsIgnoreCase("Cancelled") || booking.getStatus().equalsIgnoreCase("Completed")) {
                Room room = roomRepository.findByNumber(Integer.parseInt(existingBooking.getRoomNumber())).orElse(null);
                if (room != null) {
                    room.setStatus("Available");
                    roomRepository.save(room);
                }
            }

            return bookingRepository.save(existingBooking);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        BookingEntity booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            Room room = roomRepository.findByNumber(Integer.parseInt(booking.getRoomNumber())).orElse(null);
            if (room != null) {
                room.setStatus("Available");
                roomRepository.save(room);
            }
            bookingRepository.deleteById(id);
        }
    }

    @Override
    public List<BookingEntity> findByRoomNumber(String roomNumber) {
        return bookingRepository.findByRoomNumber(roomNumber);
    }
}
