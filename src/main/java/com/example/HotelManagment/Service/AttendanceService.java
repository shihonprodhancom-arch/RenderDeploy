//package com.example.HotelManagment.Service;
//
//import com.example.HotelManagment.Entity.Attendance;
//import com.example.HotelManagment.Repository.AttendanceRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AttendanceService {
//
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    public Attendance saveAttendance(Attendance attendance) {
//        return attendanceRepository.save(attendance);
//    }
//
//    public List<Attendance> getAllAttendance() {
//        return attendanceRepository.findAll();
//    }
//
//    public Optional<Attendance> getAttendanceById(Long id) {
//        return attendanceRepository.findById(id);
//    }
//
//    public List<Attendance> getAttendanceByStaffId(Long staffId) {
//        return attendanceRepository.findByStaffId(staffId);
//    }
//
//    public void deleteAttendance(Long id) {
//        attendanceRepository.deleteById(id);
//    }
//}


package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.Attendance;
import com.example.HotelManagment.Entity.Staff;
import com.example.HotelManagment.Repository.AttendanceRepository;
import com.example.HotelManagment.Repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StaffRepository staffRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StaffRepository staffRepository) {
        this.attendanceRepository = attendanceRepository;
        this.staffRepository = staffRepository;
    }

    public Attendance addAttendance(Long staffId, LocalDate date, String status) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found: " + staffId));
        Attendance a = new Attendance();

        a.setDate(date != null ? date : LocalDate.now());
        a.setStatus(status);
        return attendanceRepository.save(a);
    }

    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getById(Long id) {
        return attendanceRepository.findById(id);
    }

    public List<Attendance> getByStaffId(Long staffId) {
        return attendanceRepository.findByStaffId(staffId);
    }

    public List<Attendance> getByDate(LocalDate date) {
        return attendanceRepository.findByDate(date);
    }

    public void delete(Long id) {
        attendanceRepository.deleteById(id);
    }
}
