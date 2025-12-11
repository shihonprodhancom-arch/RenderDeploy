package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.Attendance;
import com.example.HotelManagment.Repository.AttendanceRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceReportService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceReportService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public ByteArrayInputStream generateReport(String format) throws JRException {
        List<Attendance> attendances = attendanceRepository.findAll();

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(attendances);

        // Jasper report design (path to your .jrxml)
        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/resources/reports/attendance.jrxml");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Hotel Management System");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        switch (format.toLowerCase()) {
            case "pdf":
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
                break;
            case "xlsx":
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
                exporter.exportReport();
                break;
            case "csv":
                JRCsvExporter csvExporter = new JRCsvExporter();
                csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                csvExporter.setExporterOutput(new SimpleWriterExporterOutput(out));
                csvExporter.exportReport();
                break;
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
