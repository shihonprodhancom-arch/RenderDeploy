package com.example.HotelManagment.Controller;

import com.example.HotelManagment.Entity.ExtraService;
import com.example.HotelManagment.Repository.ExtraServiceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:4200")
public class ExtraServiceController {

    private final ExtraServiceRepository serviceRepo;

    public ExtraServiceController(ExtraServiceRepository serviceRepo) {
        this.serviceRepo = serviceRepo;
    }

    @GetMapping
    public List<ExtraService> getAllServices() {
        return serviceRepo.findAll();
    }

    @PostMapping
    public ExtraService addService(@RequestBody ExtraService service) {
        return serviceRepo.save(service);
    }

    // Delete service
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Long id) {
        serviceRepo.deleteById(id);
    }

}
