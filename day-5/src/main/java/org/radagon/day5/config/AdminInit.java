package org.radagon.day5.config;

import lombok.AllArgsConstructor;
import org.radagon.day5.entity.User;
import org.radagon.day5.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    Создаёт админа по умолчанию, если его ещё нет
    в БД
     */

    @Override
    public void run(String... args) {
        if (userRepository.findByUserEmail("admin@admin.com").isEmpty()) {
            User admin = User.builder()
                    .userName("Admin")
                    .userEmail("admin@admin.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role("ROLE_ADMIN")
                    .build();

            userRepository.save(admin);
        }
    }
}
