package com.hotel.hotel_management.repository;

import com.hotel.hotel_management.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
    List<Venue> findByStatus(Venue.VenueStatus status);
    Venue findByName(String name);
    boolean existsByName(String name);

    boolean existsByNameAndOwnerId(String name, String ownerId);
}