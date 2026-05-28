package com.hotel.hotel_management.dto.request.CourtResponse;

import com.hotel.hotel_management.entity.Booking;
import com.hotel.hotel_management.entity.Court;
import com.hotel.hotel_management.entity.SportType;
import com.hotel.hotel_management.entity.Venue;
import com.hotel.hotel_management.validator.DobContraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtCreationRequest {

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
