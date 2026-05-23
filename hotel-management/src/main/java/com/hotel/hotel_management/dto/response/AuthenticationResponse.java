package com.hotel.hotel_management.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
