package com.hotel.hotel_management.dto.response;

import com.hotel.hotel_management.entity.Room;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomResponse {
    Long id;
    String roomNumber;
    Room.RoomType type;
    Double pricePerNight;
    Room.RoomStatus status;
    String description;
}
