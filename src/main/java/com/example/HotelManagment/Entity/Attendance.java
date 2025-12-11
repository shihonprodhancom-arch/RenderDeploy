//package com.example.HotelManagment.Entity;//package com.example.HotelManagment.Entity;
////
////import jakarta.persistence.*;
////import lombok.Data;
////import java.time.LocalDate;
////
////@Data
////@Entity
////@Table(name="attendance")
////public class Attendance {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long id;   // ✅ এইটা add করতে হবে
////
////    @ManyToOne
////    @JoinColumn(name="staff_id")
////    private Staff staff;
////
////    private LocalDate date;
////    private boolean present;
////    private String dutyShift; // Morning / Night
////}
//
//
//
//import jakarta.persistence.*;
//import java.time.LocalDate;
//
//@Entity
//@Table(name = "attendance")
//public class Attendance {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private LocalDate date; // <-- change here
//
//    private String dutyShift;
//    private Boolean present;
//
//    @ManyToOne
//    @JoinColumn(name = "staff_id")
//    private Staff staff;
//
//    // Getter & Setter
//    public Long getId() { return id; }
//    public void setId(Long id) { this.id = id; }
//
//    public LocalDate getDate() { return date; }
//    public void setDate(LocalDate date) { this.date = date; }
//
//    public String getDutyShift() { return dutyShift; }
//    public void setDutyShift(String dutyShift) { this.dutyShift = dutyShift; }
//
//    public Boolean getPresent() { return present; }
//    public void setPresent(Boolean present) { this.present = present; }
//
//    public Staff getStaff() { return staff; }
//    public void setStaff(Staff staff) { this.staff = staff; }
//}
//


package com.example.HotelManagment.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate date;
    private String status;
    private String dutyShift;
    private Long staffId;
    private Boolean present = false;
    @Transient
    private String staffName; // optional for frontend display


}

