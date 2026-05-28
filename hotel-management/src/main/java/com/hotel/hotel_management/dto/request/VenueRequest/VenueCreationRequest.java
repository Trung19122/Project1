package com.hotel.hotel_management.dto.request.VenueRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VenueCreationRequest {
    @NotBlank(message = "VENUE_NAME_REQUIRED")
    String name;

    @NotBlank(message = "ADDRESS_REQUIRED")
    String address;

    @Size(max = 1000, message = "DESCRIPTION_TOO_LONG")
    String description;

    String phone;

    LocalTime openTime;

    LocalTime closeTime;


}
