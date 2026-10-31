package amitab.drive.services;

import amitab.drive.dto.RegisterRequestDTO;
import amitab.drive.dto.RegisterResponseDTO;
import amitab.drive.entity.User;
import amitab.drive.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponseDTO registerUser(@Valid RegisterRequestDTO body) {
        userRepository.existsByEmail(User.normalizeEmail(body.email()))
                .orElseThrow(() -> new RuntimeException("User with the email exists"));

        User u = new User();
        u.setFullName(body.fullName());
        u.setEmail(User.normalizeEmail(body.email()));
        u.setPassword(passwordEncoder.encode(body.password()));

        User createdUser = userRepository.save(u);
        return RegisterResponseDTO.from(createdUser);
    }

}
