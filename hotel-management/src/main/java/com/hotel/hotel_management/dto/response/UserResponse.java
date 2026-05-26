package com.hotel.hotel_management.dto.response;

import com.hotel.hotel_management.Enum.UserStatus;
import com.hotel.hotel_management.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    LocalDate dob;
    String email;
    String phone;
    String avatarUrl;
    UserStatus status;
    Set<RoleResponse> roles;
}
