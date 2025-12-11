package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.ExtraService;
import com.example.HotelManagment.Repository.ExtraServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtraServiceService {

    private final ExtraServiceRepository repository;

    public ExtraServiceService(ExtraServiceRepository repository) {
        this.repository = repository;
    }

    public List<ExtraService> getAll() {
        return repository.findAll();
    }

    public ExtraService save(ExtraService service) {
        return repository.save(service);
    }
}
