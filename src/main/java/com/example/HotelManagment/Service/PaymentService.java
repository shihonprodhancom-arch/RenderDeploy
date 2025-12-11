package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.PaymentEntity;
import com.example.HotelManagment.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public List<PaymentEntity> getAllPayments() {
        return repository.findAll();
    }

    public PaymentEntity getPaymentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public PaymentEntity savePayment(PaymentEntity payment) {
        return repository.save(payment);
    }

    public PaymentEntity updatePayment(Long id, PaymentEntity payment) {
        PaymentEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        existing.setGuest(payment.getGuest());
        existing.setAmount(payment.getAmount());
        existing.setMethod(payment.getMethod());
        existing.setDate(payment.getDate());
        existing.setBankName(payment.getBankName());
        existing.setCardNumber(payment.getCardNumber());
        existing.setExpiryDate(payment.getExpiryDate());
        existing.setAccountNumber(payment.getAccountNumber());
        existing.setTransactionId(payment.getTransactionId());

        return repository.save(existing);
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }
}
