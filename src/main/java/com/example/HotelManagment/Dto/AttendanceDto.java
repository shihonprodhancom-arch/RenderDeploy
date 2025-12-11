package com.example.HotelManagment.Dto;

import java.time.LocalDate;

public class AttendanceDto {
    private Long staffId;
    private LocalDate date; // optional: if null backend uses LocalDate.now()
    private String status; // Present / Absent / Leave

    // getters & setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
