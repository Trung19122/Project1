package com.hotel.hotel_management.dto.response;

import com.hotel.hotel_management.entity.Court;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourtResponse {
    private Long id;

    private String name;

    private String courtType;

    private String description;

    private Court.CourtStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private VenueSummary venue;

    private SportTypeSummary sportType;

    @Data
    @Builder
    public static class VenueSummary {
        private Long id;
        private String name;
        private String address;
    }

    @Data
    @Builder
    public static class SportTypeSummary {
        private Long id;
        private String name;
    }
}
