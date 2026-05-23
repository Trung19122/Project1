package com.hotel.hotel_management.service;

import com.hotel.hotel_management.Mapper.RoomMapper;
import com.hotel.hotel_management.dto.request.RoomCreationRequest;
import com.hotel.hotel_management.dto.request.RoomUpdateRequest;
import com.hotel.hotel_management.dto.response.RoomResponse;
import com.hotel.hotel_management.entity.Room;
import com.hotel.hotel_management.exception.AppException;
import com.hotel.hotel_management.exception.ErrorCode;
import com.hotel.hotel_management.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }
    public Room createRoom(RoomCreationRequest request){
        Room room = roomMapper.toRoom(request);

        if(roomRepository.existsByRoomNumber(request.getRoomNumber())){
            throw new AppException(ErrorCode.ROOM_EXISTED);
        }

        return roomRepository.save(room);
    }

    public List<Room> getRoom(){
        return roomRepository.findAll();
    }

    public RoomResponse getRoomByID(long id){
        return roomMapper.toRoomResponse(roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND)));
    }

    public boolean deleteRoom(long id){

        if (!roomRepository.existsById(id)) {
            return false;
        }

        roomRepository.deleteById(id);
        return true;
    }

    public RoomResponse upDateRoom(long id, RoomUpdateRequest request) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        if (request.getRoomNumber() != null && roomRepository.existsByRoomNumber(request.getRoomNumber()) &&
                !room.getRoomNumber().equals(request.getRoomNumber())) {
            throw new AppException(ErrorCode.ROOM_EXISTED);
        }

        roomMapper.updateRoom(room, request);

        return roomMapper.toRoomResponse(roomRepository.save(room));
    }
}
