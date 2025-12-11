package com.example.HotelManagment.Entity;


//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDate;
//
//@Data
//@Entity
//@Table(name = "staff")
//public class Staff {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//    private String role;     // e.g., Receptionist, Cleaner, Chef, etc.
//    private Double salary;
//    private String shift;    // e.g., Morning / Night
//    private LocalDate joinDate = LocalDate.now();
//}


import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;
    private Double salary;
    private String shift;

    @Temporal(TemporalType.DATE)
    private Date joinDate;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
}
