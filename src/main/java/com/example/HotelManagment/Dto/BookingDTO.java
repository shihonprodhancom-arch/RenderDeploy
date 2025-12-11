package com.example.HotelManagment.Dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data

public class BookingDTO {

    private Long id;
    private String guestName;
    private String roomNumber;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Double totalPrice;
    private String status;
    private String paymentMethod;
    private String paymentInfo;
}
