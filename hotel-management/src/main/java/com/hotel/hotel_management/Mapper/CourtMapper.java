package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.CourtResponse.CourtCreationRequest;
import com.hotel.hotel_management.dto.request.CourtResponse.CourtUpdateRequest;
import com.hotel.hotel_management.dto.response.CourtResponse;
import com.hotel.hotel_management.entity.Court;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourtMapper {

    Court toCourt(CourtCreationRequest request);

    CourtResponse toCourtResponse(Court court);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "sportType", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateCourt(
            @MappingTarget Court court,
            CourtUpdateRequest request
    );
}
