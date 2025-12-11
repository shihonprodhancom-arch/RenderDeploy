package com.example.HotelManagment.Repository.RoomTypeRepository;

import com.example.HotelManagment.Entity.newEntitys.RoomGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomGroupRepository extends JpaRepository<RoomGroup, Long> {
    Optional<RoomGroup> findByTypeIgnoreCase(String type);
    boolean existsByTypeAndIdNot(String type, Long id);

    // Optional: Check if type exists (for create operations)
    boolean existsByType(String type);

}
