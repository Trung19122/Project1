package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.SportTypeRequest.SportTypeCreationRequest;
import com.hotel.hotel_management.dto.request.SportTypeRequest.SportTypeUpdateRequest;
import com.hotel.hotel_management.dto.response.SportTypeResponse;
import com.hotel.hotel_management.entity.SportType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SportTypeMapper {
    SportType toSportType(SportTypeCreationRequest request);

    SportTypeResponse toSportTypeResponse(SportType sportType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSportType(@MappingTarget SportType sportType, SportTypeUpdateRequest request);
}
