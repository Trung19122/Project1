package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.RoomCreationRequest;
import com.hotel.hotel_management.dto.request.RoomUpdateRequest;
import com.hotel.hotel_management.dto.response.RoomResponse;
import com.hotel.hotel_management.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper {
        Room toRoom(RoomCreationRequest request);
        RoomResponse toRoomResponse(Room room);
        void updateRoom(@MappingTarget Room room, RoomUpdateRequest request);

}
