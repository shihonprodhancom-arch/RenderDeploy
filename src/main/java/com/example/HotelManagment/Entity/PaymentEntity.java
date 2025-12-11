package com.example.HotelManagment.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guest;
    private Double amount;
    private String method;
    private String date; // keep as String in yyyy-MM-dd format

    // Card fields (optional)
    private String bankName;
    private String cardNumber;
    private String expiryDate;

    // Mobile/Online fields (optional)
    private String accountNumber;
    private String transactionId;
}
