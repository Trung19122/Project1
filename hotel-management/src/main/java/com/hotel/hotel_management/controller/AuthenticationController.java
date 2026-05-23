package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.request.AuthenticationRequest;
import com.hotel.hotel_management.dto.request.InstrospectRequest;
import com.hotel.hotel_management.dto.response.AuthenticationResponse;
import com.hotel.hotel_management.dto.response.InstrospectResponse;
import com.hotel.hotel_management.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var res = authenticationService.isAuthenticated(request);
        return ApiResponse.<AuthenticationResponse> builder()
                .result(res)
                .build();
    }
    @PostMapping("/instrospect")
    ApiResponse<InstrospectResponse> authenticate(@RequestBody InstrospectRequest request) throws ParseException, JOSEException {
        var res = authenticationService.instrospect(request);
        return ApiResponse.<InstrospectResponse> builder()
                .result(res)
                .build();
    }
}
