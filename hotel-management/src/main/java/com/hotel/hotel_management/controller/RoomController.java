package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.request.RoomCreationRequest;
import com.hotel.hotel_management.dto.request.RoomUpdateRequest;
import com.hotel.hotel_management.dto.response.RoomResponse;
import com.hotel.hotel_management.entity.Room;
import com.hotel.hotel_management.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }
    @PostMapping("/room")
    ApiResponse<Room> createRoom(@RequestBody @Valid RoomCreationRequest request){
        ApiResponse<Room> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.createRoom(request));
        return apiResponse;
    }

    @GetMapping("/room")
    ApiResponse<List<Room>> getRoom(){
        ApiResponse<List<Room>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.getRoom());
        return apiResponse;
    }

    @GetMapping("/room/{roomID}")
    ApiResponse<RoomResponse> getRoomByID(@PathVariable long roomID){
        ApiResponse<RoomResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.getRoomByID(roomID));
        return apiResponse;
    }

    @PutMapping("/room/{roomID}")
    ApiResponse<RoomResponse> updateRoom(@PathVariable long roomID, @RequestBody @Valid RoomUpdateRequest request){
        ApiResponse<RoomResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(roomService.upDateRoom(roomID,request));
        return apiResponse;
    }



    @DeleteMapping("/room/{roomID}")
    String deleteRoom(@PathVariable long roomID){
        if (!roomService.deleteRoom(roomID)){
            return "Xóa thất bại";
        }

        return "Thành công";
    }

}
