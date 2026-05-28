package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.request.SportTypeRequest.SportTypeCreationRequest;
import com.hotel.hotel_management.dto.request.SportTypeRequest.SportTypeUpdateRequest;
import com.hotel.hotel_management.dto.response.SportTypeResponse;
import com.hotel.hotel_management.service.SportTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sport-types")
@RequiredArgsConstructor
public class SportTypeController {
    private final SportTypeService sportTypeService;

    @PostMapping
    public ApiResponse<SportTypeResponse> create(@RequestBody @Valid SportTypeCreationRequest request) {
        return ApiResponse.<SportTypeResponse>builder()
                .result(sportTypeService.createSportType(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<SportTypeResponse> getById(@PathVariable Long id) {
        return ApiResponse.<SportTypeResponse>builder()
                .result(sportTypeService.getSportTypeById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<SportTypeResponse>> getAll() {
        return ApiResponse.<List<SportTypeResponse>>builder()
                .result(sportTypeService.getSportTypes())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SportTypeResponse> update(
            @PathVariable Long id,
            @RequestBody @Valid SportTypeUpdateRequest request) {
        return ApiResponse.<SportTypeResponse>builder()
                .result(sportTypeService.updateSportType(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        sportTypeService.deleteSportType(id);
        return ApiResponse.<Void>builder().build();
    }
}
