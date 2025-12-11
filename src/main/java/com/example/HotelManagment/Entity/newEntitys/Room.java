//package com.example.HotelManagment.Entity.newEntitys;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Table(name = "rooms")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Room {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, unique = true)
//    private Integer number;
//
//    private Double price;
//
//    private String type;
//
//
//
//    private Integer capacity;
//
//    @ElementCollection
//    @CollectionTable(name = "room_services", joinColumns = @JoinColumn(name = "room_id"))
//    @Column(name = "service")
//    private List<String> services;
//
//    private String image;
//
//    private String status = "Available";
//
//    @ManyToOne
//    @JoinColumn(name = "room_type_id", nullable = false)
//    private RoomGroup roomGroup;
//
//    // ✅ Custom Constructor (status সহ)
//    public Room(Integer number, Double price, Integer capacity, List<String> services, String image, String status, RoomGroup roomGroup) {
//        this.number = number;
//        this.price = price;
//        this.capacity = capacity;
//        this.services = services;
//        this.image = image;
//        this.status = status;
//        this.roomGroup = roomGroup;
//    }
//}


package com.example.HotelManagment.Entity.newEntitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer number;

    private Double price;

    private String type;

    private Integer capacity;

    @ElementCollection
    @CollectionTable(name = "room_services", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "service")
    private List<String> services;

    private String image;

    private String status = "Available"; // ✅ এখানে রাখবে

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomGroup roomGroup;

    // Custom constructor
    public Room(Integer number, Double price, Integer capacity, List<String> services, String image, String status, RoomGroup roomGroup) {
        this.number = number;
        this.price = price;
        this.capacity = capacity;
        this.services = services;
        this.image = image;
        this.status = status;
        this.roomGroup = roomGroup;
    }
}
