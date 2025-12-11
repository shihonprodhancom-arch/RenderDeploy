package com.example.HotelManagment.Service;

import com.example.HotelManagment.Dto.BookingDTO;
import com.example.HotelManagment.Entity.BookingEntity;
import com.example.HotelManagment.Repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class ReportService {
    private final BookingRepository bookingRepository;

    public JasperPrint getJasperPrint() throws IOException, JRException {

        InputStream reportStream = new ClassPathResource("reports/bookings.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        List<BookingEntity> booking = bookingRepository.findAll();

        List<BookingDTO> bookingDTOS = booking.stream()
                .map(booking1 -> BookingDTO.builder()
                        .id(booking1.getId())
                        .guestName(booking1.getGuestName())
                        .roomNumber(booking1.getRoomNumber())
                        .checkInDate(booking1.getCheckInDate())
                        .checkOutDate(booking1.getCheckOutDate())
                        .totalPrice(booking1.getTotalPrice())
                        .status(booking1.getStatus())
                        .paymentMethod(booking1.getPaymentMethod())
                        .paymentInfo(booking1.getPaymentInfo())
                        .build())
                .collect(Collectors.toList());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookingDTOS);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Axis Bank PLC System");

        return JasperFillManager.fillReport(jasperReport, parameters, dataSource);
    }


}
