package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.UserRequest.UserCreationRequest;
import com.hotel.hotel_management.dto.request.UserRequest.UserUpdateRequest;
import com.hotel.hotel_management.dto.response.UserResponse;
import com.hotel.hotel_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "status", ignore = true)
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "dob", source = "dob")
    @Mapping(target = "status", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
