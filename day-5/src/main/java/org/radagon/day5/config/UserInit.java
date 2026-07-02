package org.radagon.day5.config;

import lombok.AllArgsConstructor;
import org.radagon.day5.entity.User;
import org.radagon.day5.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserInit implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    Создаёт тестового юзера в БД
     */

    @Override
    public void run(String... args) {
        if (userRepository.findByUserEmail("user@user.com").isEmpty()) {
            User testUser = User.builder()
                    .userName("NewTestUser")
                    .userEmail("user@user.com")
                    .password(passwordEncoder.encode("user123"))
                    .role("ROLE_USER")
                    .build();

            userRepository.save(testUser);
        }
    }
}
