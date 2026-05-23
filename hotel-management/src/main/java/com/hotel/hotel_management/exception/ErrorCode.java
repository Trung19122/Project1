package com.hotel.hotel_management.exception;

public enum ErrorCode  {
    USER_EXISTED(1001,"User existed"),
    USER_NOT_FOUND(1006, "User not found"),
    USERNAME_INVALID(1007, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1008, "Password must be at least 8 characters"),
    ROOM_EXISTED(1004, "Phòng này đã tồn tại"),
    ROOM_NOT_FOUND(1005, "Không tìm thấy phòng"),
    NO_CATCH(9999,"Not define error"),
    ROOM_NUMBER(1002,"Số phòng phải hơn 3 ký tự "),
    DESCRIPTION(1003,"Viết nhiều mô tả hơn"),
    UNAUTHENTICATED(1009,"Wrong pass"),
    INVALID_TOKEN(1010,"Invalid token"),
    UNAUTHORIZED(1912, "Unauthorized");

    private int code ;
    private String mess;


    ErrorCode(int code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public int getCode() {
        return code;
    }

    public String getMess() {
        return mess;
    }
}
