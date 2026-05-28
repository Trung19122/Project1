package com.hotel.hotel_management.dto.request.SportTypeRequest;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SportTypeCreationRequest {
    @NotBlank(message = "SPORT_TYPE_NAME_REQUIRED")
    @Size(min = 2, max = 50, message = "SPORT_TYPE_NAME_INVALID")
    private String name;

    @Size(max = 500, message = "DESCRIPTION_TOO_LONG")
    private String description;
}
