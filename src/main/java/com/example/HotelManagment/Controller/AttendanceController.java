//package com.example.HotelManagment.Controller;
//
//import com.example.HotelManagment.Entity.Attendance;
//import com.example.HotelManagment.Service.AttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/attendance")
//public class AttendanceController {
//
//    @Autowired
//    private AttendanceService attendanceService;
//
//    @PostMapping("/add")
//    public Attendance addAttendance(@RequestBody Attendance attendance) {
//        return attendanceService.saveAttendance(attendance);
//    }
//
//    @GetMapping("/all")
//    public List<Attendance> getAllAttendance() {
//        return attendanceService.getAllAttendance();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<Attendance> getAttendanceById(@PathVariable Long id) {
//        return attendanceService.getAttendanceById(id);
//    }
//
//    @GetMapping("/staff/{staffId}")
//    public List<Attendance> getAttendanceByStaffId(@PathVariable Long staffId) {
//        return attendanceService.getAttendanceByStaffId(staffId);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteAttendance(@PathVariable Long id) {
//        attendanceService.deleteAttendance(id);
//    }
//}


package com.example.HotelManagment.Controller;


import com.example.HotelManagment.Entity.Attendance;
import com.example.HotelManagment.Repository.AttendanceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    public AttendanceController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    // Get all attendance
    @GetMapping
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    // Get attendance by staffId
    @GetMapping("/staff/{staffId}")
    public List<Attendance> getByStaff(@PathVariable Long staffId) {
        return attendanceRepository.findByStaffId(staffId);
    }

    // Add attendance
    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    // Update attendance
    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        attendance.setId(id);
        return attendanceRepository.save(attendance);
    }
}


