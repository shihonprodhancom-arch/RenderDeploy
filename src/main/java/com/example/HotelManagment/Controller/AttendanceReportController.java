package com.example.HotelManagment.Controller;

import com.example.HotelManagment.Service.AttendanceReportService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class AttendanceReportController {

    private final AttendanceReportService reportService;

    public AttendanceReportController(AttendanceReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/attendance/{format}")
    public ResponseEntity<InputStreamResource> downloadAttendanceReport(@PathVariable String format) {
        try {
            ByteArrayInputStream bis = reportService.generateReport(format);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=attendance-report." + format);

            MediaType mediaType = MediaType.APPLICATION_PDF;
            if (format.equalsIgnoreCase("xlsx")) mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            if (format.equalsIgnoreCase("csv")) mediaType = MediaType.TEXT_PLAIN;

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(mediaType)
                    .body(new InputStreamResource(bis));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
