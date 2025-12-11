package com.example.HotelManagment.Service;

import com.example.HotelManagment.Entity.GuestEntity;
import com.example.HotelManagment.Repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    public GuestServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public List<GuestEntity> getAll() {
        return guestRepository.findAll();
    }

    @Override
    public GuestEntity getById(Long id) {
        Optional<GuestEntity> guest = guestRepository.findById(id);
        return guest.orElse(null);
    }

    @Override
    public GuestEntity save(GuestEntity guest) {
        return guestRepository.save(guest);
    }

    @Override
    public GuestEntity update(Long id, GuestEntity guest) {
        return guestRepository.findById(id).map(existingGuest -> {
            existingGuest.setName(guest.getName());
            existingGuest.setPhone(guest.getPhone());
            existingGuest.setEmail(guest.getEmail());
            return guestRepository.save(existingGuest);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        guestRepository.deleteById(id);
    }
}
