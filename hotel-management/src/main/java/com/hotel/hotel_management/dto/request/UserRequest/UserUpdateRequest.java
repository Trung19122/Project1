package com.hotel.hotel_management.dto.request.UserRequest;

import com.hotel.hotel_management.Enum.UserStatus;
import com.hotel.hotel_management.validator.DobContraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    String firstName;
    String lastName;

    @DobContraint(min = 18, message = "INVALIDDOB")
    LocalDate dob;

    @Email(message = "EMAIL_INVALID")
    String email;

    String phone;
    String avatarUrl;

    UserStatus status;

    Set<String> roles;
}
