package com.hotel.hotel_management.Mapper;

import com.hotel.hotel_management.dto.request.RoleRequest;
import com.hotel.hotel_management.dto.response.RoleResponse;
import com.hotel.hotel_management.entity.Roles;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {
    Roles toRoles(RoleRequest request);
    RoleResponse toRoleResponse(Roles roles);

}
