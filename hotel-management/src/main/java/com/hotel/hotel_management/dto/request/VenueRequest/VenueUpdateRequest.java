package com.hotel.hotel_management.dto.request.VenueRequest;

import com.hotel.hotel_management.entity.Venue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class VenueUpdateRequest {
    @NotBlank(message = "VENUE_NAME_REQUIRED")
    String name;

    @NotBlank(message = "ADDRESS_REQUIRED")
    String address;

    @Size(max = 1000, message = "DESCRIPTION_TOO_LONG")
    String description;

    String phone;

    LocalTime openTime;

    LocalTime closeTime;
    Venue.VenueStatus status;
}
