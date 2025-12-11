package com.example.HotelManagment.Repository;




import com.example.HotelManagment.Entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepositoryOld extends JpaRepository<RoomEntity, Long> {
}
