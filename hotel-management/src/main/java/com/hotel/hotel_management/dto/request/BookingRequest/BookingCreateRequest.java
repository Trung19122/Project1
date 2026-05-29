package com.hotel.hotel_management.dto.request.BookingRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingCreateRequest {
    private Long courtId;

    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String note;
}
