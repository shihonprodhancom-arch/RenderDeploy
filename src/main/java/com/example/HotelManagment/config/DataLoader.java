//package com.example.HotelManagment.config;
//
//
//
//import com.example.HotelManagment.Entity.newEntitys.Room;
//import com.example.HotelManagment.Entity.newEntitys.RoomGroup;
//import com.example.HotelManagment.Repository.RoomTypeRepository.RoomGroupRepository;
//import com.example.HotelManagment.Repository.RoomTypeRepository.RoomRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private RoomGroupRepository roomGroupRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Check if data already exists to avoid duplicates
//        if (roomGroupRepository.count() == 0) {
//            loadInitialData();
//            System.out.println("Initial hotel data loaded successfully!");
//        }
//    }
//
//    private void loadInitialData() {
//        // Single Rooms
//        RoomGroup singleGroup = new RoomGroup("Single");
//
//        singleGroup.setRoom(new Room(101, 2000.0, 1,
//                Arrays.asList("WiFi", "AC"), "assets/img/download (1).jpg"));
//        singleGroup.setRoom(new Room(102, 2100.0, 1,
//                Arrays.asList("WiFi", "AC"), "assets/img/download (2).jpg"));
//        singleGroup.setRoom(new Room(103, 2200.0, 1,
//                Arrays.asList("WiFi", "AC"), "assets/img/download (3).jpg"));
//        singleGroup.setRoom(new Room(104, 2300.0, 1,
//                Arrays.asList("WiFi", "AC"), "assets/img/download (5).jpg"));
//
//        // Double Rooms
//        RoomGroup doubleGroup = new RoomGroup("Double");
//        doubleGroup.setRoom(new Room(201, 3000.0, 2,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/markus-spiske-g5ZIXjzRGds-unsplash.jpg"));
//        doubleGroup.setRoom(new Room(202, 3100.0, 2,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/manuel-moreno-DGa0LQ0yDPc-unsplash.jpg"));
//        doubleGroup.setRoom(new Room(203, 3200.0, 2,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/gettyimages-1390233984-612x612.jpg"));
//        doubleGroup.setRoom(new Room(204, 3300.0, 2,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/download (4).jpg"));
//        doubleGroup.setRoom(new Room(205, 3400.0, 2,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/gettyimages-154945734-612x612.jpg"));
//
//        // Suite Rooms
//        RoomGroup suiteGroup = new RoomGroup("Suite");
//        suiteGroup.setRoom(new Room(301, 5000.0, 4,
//                Arrays.asList("WiFi", "AC", "TV", "Mini Bar"), "assets/img/gettyimages-1148452746-612x612.jpg"));
//        suiteGroup.setRoom(new Room(302, 5100.0, 4,
//                Arrays.asList("WiFi", "AC", "TV", "Mini Bar"), "assets/img/gettyimages-1266155634-612x612.jpg"));
//        suiteGroup.setRoom(new Room(303, 5200.0, 4,
//                Arrays.asList("WiFi", "AC", "TV", "Mini Bar"), "assets/img/gettyimages-1300135335-612x612.jpg"));
//
//        // Honeymoon Rooms
//        RoomGroup honeymoonGroup = new RoomGroup("Honeymoon");
//        honeymoonGroup.setRoom(new Room(401, 6000.0, 2,
//                Arrays.asList("WiFi", "AC", "TV", "Jacuzzi"), "assets/img/download (6).jpg"));
//        honeymoonGroup.setRoom(new Room(402, 6200.0, 2,
//                Arrays.asList("WiFi", "AC", "TV", "Jacuzzi"), "assets/img/gettyimages-1334117383-612x612.jpg"));
//
//        // Family Rooms
//        RoomGroup familyGroup = new RoomGroup("Family");
//        familyGroup.setRoom(new Room(501, 7000.0, 5,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/gettyimages-1148452746-612x612.jpg"));
//        familyGroup.setRoom(new Room(502, 7100.0, 5,
//                Arrays.asList("WiFi", "AC", "TV"), "assets/img/manuel-moreno-DGa0LQ0yDPc-unsplash.jpg"));
//
//        // VIP Rooms
//        RoomGroup vipGroup = new RoomGroup("VIP");
//        vipGroup.setRoom(new Room(601, 12000.0, 3,
//                Arrays.asList("WiFi", "AC", "TV", "Mini Bar", "Private Pool"),
//                "assets/img/gettyimages-1334117383-612x612.jpg"));
//        vipGroup.setRoom(new Room(602, 12500.0, 3,
//                Arrays.asList("WiFi", "AC", "TV", "Mini Bar", "Private Pool"),
//                "assets/img/gettyimages-1390233984-612x612.jpg"));
//
//        // Save all groups
//        roomGroupRepository.saveAll(Arrays.asList(
//                singleGroup, doubleGroup, suiteGroup,
//                honeymoonGroup, familyGroup, vipGroup
//        ));
//    }
//}