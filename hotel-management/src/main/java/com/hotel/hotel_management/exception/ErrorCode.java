package com.hotel.hotel_management.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode  {
    USER_EXISTED(1001, "User already exists", HttpStatus.BAD_REQUEST),

    USER_NOT_FOUND(1002, "User not found", HttpStatus.NOT_FOUND),

    EMAIL_EXISTED(1003, "Email already exists", HttpStatus.BAD_REQUEST),

    USERNAME_INVALID(1004, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),

    PASSWORD_INVALID(1005, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),

    INVALIDDOB(1006, "User must be at least {min} years old", HttpStatus.BAD_REQUEST),

    UNAUTHENTICATED(1007, "Wrong username or password", HttpStatus.UNAUTHORIZED),

    UNAUTHORIZED(1008, "You do not have permission", HttpStatus.FORBIDDEN),

    INVALID_TOKEN(1009, "Invalid token", HttpStatus.UNAUTHORIZED),

    VENUE_EXISTED(1010, "Venue already exists", HttpStatus.BAD_REQUEST),

    VENUE_NOT_FOUND(1011, "Venue not found", HttpStatus.NOT_FOUND),

    COURT_EXISTED(1012, "Court already exists", HttpStatus.BAD_REQUEST),

    COURT_NOT_FOUND(1013, "Court not found", HttpStatus.NOT_FOUND),

    SPORT_TYPE_NOT_FOUND(1014, "Sport type not found", HttpStatus.NOT_FOUND),

    BOOKING_NOT_FOUND(1015, "Booking not found", HttpStatus.NOT_FOUND),

    BOOKING_TIME_CONFLICT(1016, "This time slot has already been booked", HttpStatus.BAD_REQUEST),

    DESCRIPTION_INVALID(1017, "Description must be more detailed", HttpStatus.BAD_REQUEST),

    NO_CATCH(9999, "Undefined error", HttpStatus.INTERNAL_SERVER_ERROR),
    COURT_ALREADY_EXISTS(1018, "Court already exists", HttpStatus.BAD_REQUEST),
    SPORT_TYPE_EXISTED(1019, "Sport type already exists", HttpStatus.BAD_REQUEST)
    ;

    private int code ;
    private String mess;


    ErrorCode(int code, String mess, HttpStatus httpStatus) {
        this.code = code;
        this.mess = mess;
        this.httpStatus = httpStatus;
    }
    private HttpStatus httpStatus;

}
