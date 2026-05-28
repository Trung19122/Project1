package com.hotel.hotel_management.repository;

import com.hotel.hotel_management.entity.Court;
import com.hotel.hotel_management.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourtRepository extends JpaRepository<Court, Long> {
    List<Court> findByStatus(Court.CourtStatus status);
    boolean existsByNameAndVenue(String name, Venue venue);
    boolean existsByName(String name);
}
