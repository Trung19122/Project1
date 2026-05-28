package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.request.CourtResponse.CourtCreationRequest;
import com.hotel.hotel_management.dto.request.CourtResponse.CourtUpdateRequest;
import com.hotel.hotel_management.dto.response.CourtResponse;
import com.hotel.hotel_management.service.CourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/court")
@RequiredArgsConstructor
public class CourtController {
    private final CourtService courtService;

    @PostMapping
    public ApiResponse<CourtResponse> createCourt(
            @RequestBody @Valid CourtCreationRequest request
    ) {
        return ApiResponse.<CourtResponse>builder()
                .result(courtService.createCourt(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CourtResponse>> getCourts() {
        return ApiResponse.<List<CourtResponse>>builder()
                .result(courtService.getCourts())
                .build();
    }

    @GetMapping("/{courtId}")
    public ApiResponse<CourtResponse> getCourtById(
            @PathVariable Long courtId
    ) {
        return ApiResponse.<CourtResponse>builder()
                .result(courtService.getCourtById(courtId))
                .build();
    }

    @PutMapping("/{courtId}")
    public ApiResponse<CourtResponse> updateCourt(
            @PathVariable Long courtId,
            @RequestBody @Valid CourtUpdateRequest request
    ) {
        return ApiResponse.<CourtResponse>builder()
                .result(courtService.updateCourt(courtId, request))
                .build();
    }

    @DeleteMapping("/{courtId}")
    public ApiResponse<String> deleteCourt(
            @PathVariable Long courtId
    ) {
        courtService.deleteCourt(courtId);

        return ApiResponse.<String>builder()
                .result("Delete court successfully")
                .build();
    }
}
