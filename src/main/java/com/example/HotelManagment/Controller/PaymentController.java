package com.example.HotelManagment.Controller;

import com.example.HotelManagment.Entity.PaymentEntity;
import com.example.HotelManagment.Service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @GetMapping
    public List<PaymentEntity> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    public PaymentEntity getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id);
    }

    @PostMapping
    public PaymentEntity createPayment(@RequestBody PaymentEntity payment) {
        return service.savePayment(payment);
    }

    @PutMapping("/{id}")
    public PaymentEntity updatePayment(@PathVariable Long id, @RequestBody PaymentEntity payment) {
        return service.updatePayment(id, payment);
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        service.deletePayment(id);
    }
}
