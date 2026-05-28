package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueCreationRequest;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueUpdateRequest;
import com.hotel.hotel_management.dto.response.VenueResponse;
import com.hotel.hotel_management.service.VenueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @PostMapping
    ApiResponse<VenueResponse> createVenue(@RequestBody @Valid VenueCreationRequest request) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return ApiResponse.<VenueResponse>builder()
                .result(venueService.createVenue(request, username))
                .build();
    }

    @GetMapping
    ApiResponse<List<VenueResponse>> getVenues() {
        ApiResponse<List<VenueResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(venueService.getVenues());
        return apiResponse;
    }

    @GetMapping("/{venueId}")
    ApiResponse<VenueResponse> getVenueById(@PathVariable Long venueId) {
        ApiResponse<VenueResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(venueService.getVenueById(venueId));
        return apiResponse;
    }

    @PutMapping("/{venueId}")
    ApiResponse<VenueResponse> updateVenue(
            @PathVariable Long venueId,
            @RequestBody @Valid VenueUpdateRequest request
    ) {
        ApiResponse<VenueResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(venueService.updateVenue(venueId, request));
        return apiResponse;
    }

    @DeleteMapping("/{venueId}")
    ApiResponse<String> deleteVenue(@PathVariable Long venueId) {
        venueService.deleteVenue(venueId);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setResult("Xóa venue thành công");
        return apiResponse;
    }

}
