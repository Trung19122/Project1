package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.UserCreationRequest;
import com.hotel.hotel_management.dto.request.UserUpdateRequest;
import com.hotel.hotel_management.dto.response.UserResponse;
import com.hotel.hotel_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
