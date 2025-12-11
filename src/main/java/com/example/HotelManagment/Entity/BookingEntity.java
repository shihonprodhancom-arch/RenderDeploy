//package com.example.HotelManagment.Entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "bookings")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class BookingEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String guestName;
//
//    private String roomNumber;
//
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate checkInDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate checkOutDate;
//
//    private Double totalPrice;
//
//    // New fields
//    private String status; // e.g., Pending, Paid, Cancelled
//    private String paymentMethod; // e.g., Bank, Bkash, Nagad
//
//    @Lob
//    private String paymentInfo; // Store JSON as string (bank details or trxId)
//}


package com.example.HotelManagment.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;

    private String roomNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;

    private Double totalPrice;

    private String status; // Pending, Paid, Cancelled

    private String paymentMethod;

    @Lob
    private String paymentInfo;
}
