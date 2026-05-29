package com.hotel.hotel_management.dto.response;

import com.hotel.hotel_management.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long id;

    private Long customerId;
    private String customerName;

    private Long courtId;
    private String courtName;

    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private BigDecimal totalPrice;

    private Booking.BookingStatus status;

    private String note;

    private LocalDateTime createdAt;
}
