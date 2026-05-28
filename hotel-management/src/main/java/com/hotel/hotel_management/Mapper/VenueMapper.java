package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.VenueRequest.VenueCreationRequest;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueUpdateRequest;
import com.hotel.hotel_management.dto.response.VenueResponse;
import com.hotel.hotel_management.entity.Venue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VenueMapper {
        @Mapping(target = "id", ignore = true)
        @Mapping(target = "owner", ignore = true)
        @Mapping(target = "status", ignore = true)
        @Mapping(target = "courts", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        Venue toVenue(VenueCreationRequest request);

        VenueResponse toVenueResponse(Venue venue);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "owner", ignore = true)
        @Mapping(target = "courts", ignore = true)
        @Mapping(target = "createdAt", ignore = true)
        @Mapping(target = "updatedAt", ignore = true)
        void updateVenue(@MappingTarget Venue venue, VenueUpdateRequest request);

}
