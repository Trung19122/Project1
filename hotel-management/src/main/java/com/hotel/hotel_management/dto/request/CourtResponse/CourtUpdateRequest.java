package com.hotel.hotel_management.dto.request.CourtResponse;

import com.hotel.hotel_management.Enum.UserStatus;
import com.hotel.hotel_management.entity.Court;
import com.hotel.hotel_management.validator.DobContraint;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtUpdateRequest {
    @NotNull(message = "VENUE_REQUIRED")
    private Long venueId;

    @NotNull(message = "SPORT_TYPE_REQUIRED")
    private Long sportTypeId;

    @NotBlank(message = "COURT_NAME_REQUIRED")
    private String name;

    private String courtType;

    private String description;

    private Court.CourtStatus status;
}
