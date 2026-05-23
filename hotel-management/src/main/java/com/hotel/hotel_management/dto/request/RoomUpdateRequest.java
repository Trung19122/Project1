package com.hotel.hotel_management.dto.request;

import com.hotel.hotel_management.entity.Room;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateRequest {
    @Size(min = 3 , message = "ROOM_NUMBER")
    String roomNumber;
    Room.RoomType type;
    Double pricePerNight;
    Room.RoomStatus status;
    @Size(min = 5 , message = "DESCRIPTION")
    String description;
}
