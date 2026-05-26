package com.hotel.hotel_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", nullable = false, unique = true)
    private String roomNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Column(name = "price_per_hour", nullable = false)
    private Double pricePerHour;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @Column
    private String description;

    public enum RoomType {
        SINGLE, COUPLE, STANDARD, MEDIUM, LARGE
    }

    public enum RoomStatus {
        AVAILABLE, OCCUPIED, RESERVED,CLEANING
    }
}