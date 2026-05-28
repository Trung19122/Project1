package com.hotel.hotel_management.dto.response;

import com.hotel.hotel_management.entity.Venue;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VenueResponse {
    Long id;

    String name;

    String address;

    String description;

    String phone;

    LocalTime openTime;

    LocalTime closeTime;

    Venue.VenueStatus status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    UserResponse owner;
}
