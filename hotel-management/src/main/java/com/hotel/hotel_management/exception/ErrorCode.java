package com.hotel.hotel_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode  {
    USER_EXISTED(1001,"User existed",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1006, "User not found",HttpStatus.NOT_FOUND),
    USERNAME_INVALID(1007, "Username must be at least {min} characters",HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1008, "Password must be at least {min} characters",HttpStatus.BAD_REQUEST),
    ROOM_EXISTED(1004, "Phòng này đã tồn tại",HttpStatus.BAD_REQUEST),
    ROOM_NOT_FOUND(1005, "Không tìm thấy phòng",HttpStatus.NOT_FOUND),
    NO_CATCH(9999,"Not define error",HttpStatus.INTERNAL_SERVER_ERROR),
    ROOM_NUMBER(1002,"Số phòng phải hơn {min} ký tự ",HttpStatus.BAD_REQUEST),
    DESCRIPTION(1003,"Viết nhiều mô tả hơn",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1009,"Wrong pass",HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(1010,"Invalid token",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1912, "Ban ko co quyen",HttpStatus.UNAUTHORIZED),
    INVALIDDOB(1011, "Phai lon hon {min} tuoi",HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1013, "Email da ton tai",HttpStatus.BAD_REQUEST);

    private int code ;
    private String mess;


    ErrorCode(int code, String mess, HttpStatus httpStatus) {
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }
    private HttpStatus httpStatus;

}
