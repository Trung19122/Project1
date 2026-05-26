package com.hotel.hotel_management.configuration;

import com.hotel.hotel_management.Enum.UserStatus;
import com.hotel.hotel_management.entity.Roles;
import com.hotel.hotel_management.entity.User;
import com.hotel.hotel_management.repository.RoleRepository;
import com.hotel.hotel_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    @Transactional
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Roles adminRole = roleRepository.findById("ADMIN")
                        .orElseGet(() -> {
                            Roles newRole = Roles.builder()
                                    .name("ADMIN")
                                    .description("Administrator role")
                                    .build();
                            return roleRepository.save(newRole);
                        });

                var roles = new HashSet<Roles>();
                roles.add(adminRole);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .firstName("System")
                        .lastName("Admin")
                        .email("admin@sporthub.com")
                        .phone("099999999")
                        .status(UserStatus.ACTIVE)
                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.info("Admin user created successfully");
            }
        };
    }
}
