package com.hotel.hotel_management.dto.request;

import com.hotel.hotel_management.entity.Roles;
import com.hotel.hotel_management.validator.DobContraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class UserCreationRequest {
    @Size(min = 5, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;

    @NotBlank(message = "FIRST_NAME_REQUIRED")
    String firstName;

    @NotBlank(message = "LAST_NAME_REQUIRED")
    String lastName;

    @DobContraint(min = 17, message = "INVALIDDOB")
    LocalDate dob;

    @Email(message = "EMAIL_INVALID")
    String email;

    String phone;
    String avatarUrl;

    Set<String> roles;
}
