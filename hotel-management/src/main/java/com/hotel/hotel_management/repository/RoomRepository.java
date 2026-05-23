package com.hotel.hotel_management.repository;

import com.hotel.hotel_management.entity.Room;
import com.hotel.hotel_management.entity.Room.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStatus(RoomStatus status);
    boolean existsByRoomNumber(String RoomNumber);
}