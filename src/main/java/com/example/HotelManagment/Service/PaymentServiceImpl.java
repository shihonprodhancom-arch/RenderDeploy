//package com.example.HotelManagment.Service;
//
//import com.example.HotelManagment.Entity.PaymentEntity;
//import com.example.HotelManagment.Repository.PaymentRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PaymentServiceImpl implements PaymentService {
//
//    private final PaymentRepository repository;
//
//    public PaymentServiceImpl(PaymentRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public List<PaymentEntity> getAllPayments() {
//        return repository.findAll();
//    }
//
//    @Override
//    public PaymentEntity getPaymentById(Long id) {
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
//    }
//
//    @Override
//    public PaymentEntity savePayment(PaymentEntity payment) {
//        return repository.save(payment);
//    }
//
//    @Override
//    public PaymentEntity updatePayment(Long id, PaymentEntity payment) {
//        PaymentEntity existing = repository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Payment not found"));
//        existing.setGuest(payment.getGuest());
//        existing.setAmount(payment.getAmount());
//        existing.setMethod(payment.getMethod());
//        existing.setDate(payment.getDate());
//        existing.setBankName(payment.getBankName());
//        existing.setCardNumber(payment.getCardNumber());
//        existing.setExpiryDate(payment.getExpiryDate());
//        existing.setAccountNumber(payment.getAccountNumber());
//        existing.setTransactionId(payment.getTransactionId());
//        existing.setBookingId(payment.getBookingId()); // optional
//        return repository.save(existing);
//    }
//
//    @Override
//    public void deletePayment(Long id) {
//        repository.deleteById(id);
//    }
//}
