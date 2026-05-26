package com.hotel.hotel_management.service;


import com.hotel.hotel_management.Enum.UserStatus;
import com.hotel.hotel_management.Mapper.UserMapper;
import com.hotel.hotel_management.dto.request.UserCreationRequest;
import com.hotel.hotel_management.dto.request.UserUpdateRequest;
import com.hotel.hotel_management.dto.response.UserResponse;
import com.hotel.hotel_management.entity.Roles;
import com.hotel.hotel_management.entity.User;
import com.hotel.hotel_management.exception.AppException;
import com.hotel.hotel_management.exception.ErrorCode;
import com.hotel.hotel_management.repository.RoleRepository;
import com.hotel.hotel_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED); // Bạn cần thêm mã này vào ErrorCode.java
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        HashSet<Roles> roles = new HashSet<>();
        Roles userRole = roleRepository.findById("CUSTOMER")
                .orElseGet(() -> {
                    Roles newRole = Roles.builder()
                            .name("CUSTOMER")
                            .description("Default CUSTOMER role")
                            .build();
                    return roleRepository.save(newRole);
                });

        roles.add(userRole);

        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }
    @PostAuthorize("returnObject.username == authentication.name or hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }
    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);
        if (request.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }
}
