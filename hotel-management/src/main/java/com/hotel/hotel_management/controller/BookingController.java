package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.response.BookingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ApiResponse<BookingResponse> createBooking(
            @RequestBody @Valid BookingCreationRequest request
    ) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.createBooking(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BookingResponse>> getBookings() {
        return ApiResponse.<List<BookingResponse>>builder()
                .result(bookingService.getBookings())
                .build();
    }

    @GetMapping("/{bookingId}")
    public ApiResponse<BookingResponse> getBookingById(
            @PathVariable Long bookingId
    ) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.getBookingById(bookingId))
                .build();
    }

    @PutMapping("/{bookingId}")
    public ApiResponse<BookingResponse> updateBooking(
            @PathVariable Long bookingId,
            @RequestBody @Valid BookingUpdateRequest request
    ) {
        return ApiResponse.<BookingResponse>builder()
                .result(bookingService.updateBooking(bookingId, request))
                .build();
    }

    @DeleteMapping("/{bookingId}")
    public ApiResponse<String> deleteBooking(
            @PathVariable Long bookingId
    ) {
        bookingService.deleteBooking(bookingId);

        return ApiResponse.<String>builder()
                .result("Delete booking successfully")
                .build();
    }
}
