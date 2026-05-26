package com.hotel.hotel_management.controller;

import com.hotel.hotel_management.dto.request.ApiResponse;
import com.hotel.hotel_management.dto.request.RoleRequest;
import com.hotel.hotel_management.dto.response.RoleResponse;
import com.hotel.hotel_management.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Slf4j
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getRoles() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }


    @DeleteMapping("/{Role}")
    ApiResponse<Void> deleteRole(@PathVariable("Role") String Role) {
        roleService.delete(Role);
        return ApiResponse.<Void>builder()
                .build();
    }
}
