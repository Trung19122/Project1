package com.hotel.hotel_management.service;

import com.hotel.hotel_management.Mapper.RoleMapper;
import com.hotel.hotel_management.dto.request.RoleRequest;
import com.hotel.hotel_management.dto.response.RoleResponse;
import com.hotel.hotel_management.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    public RoleResponse create (RoleRequest request ){
        try {
            var role = roleMapper.toRoles(request);
            role = roleRepository.save(role); // Gán ngược lại để lấy dữ liệu sau khi save
            return roleMapper.toRoleResponse(role);
        } catch (Exception e) {
            // In lỗi ra console để bạn đọc
            e.printStackTrace();
            throw e; // Ném tiếp để xem nó là lỗi gì
        }
    }

    public List<RoleResponse> getAll(){
        var role = roleRepository.findAll();
        return role.stream().map(roleMapper::toRoleResponse).toList();

    }

    public void delete(String role){
        roleRepository.deleteById(role);
    }
}
