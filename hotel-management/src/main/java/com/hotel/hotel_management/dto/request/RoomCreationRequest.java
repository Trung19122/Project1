package com.hotel.hotel_management.dto.request;

import com.hotel.hotel_management.entity.Room;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomCreationRequest {
    @Size(min = 3 , message = "ROOM_NUMBER")
    String roomNumber;
    Room.RoomType type;
    Double pricePerHour;
    Room.RoomStatus status;
    @Size(min = 5 , message = "DESCRIPTION")
    String description;


}
