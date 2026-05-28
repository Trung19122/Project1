package com.hotel.hotel_management.repository;

import com.hotel.hotel_management.entity.Court;
import com.hotel.hotel_management.entity.SportType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SportTypeRepository extends JpaRepository<SportType, Long> {
    boolean existsByName(String name);
    Optional<SportType> findByName(String name);
}
