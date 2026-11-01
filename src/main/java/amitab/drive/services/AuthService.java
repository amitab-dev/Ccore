package amitab.drive.services;

import amitab.drive.dto.LoginRequestDTO;
import amitab.drive.dto.RegisterRequestDTO;
import amitab.drive.dto.RegisterResponseDTO;
import amitab.drive.dto.TokenPair;
import amitab.drive.entity.User;
import amitab.drive.exception.BadRequestException;
import amitab.drive.exception.ConflictException;
import amitab.drive.exception.NotFoundException;
import amitab.drive.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public RegisterResponseDTO registerUser(@Valid RegisterRequestDTO body) {
        String email = User.normalizeEmail(body.email());
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("User with the email exists");
        }
        User u = new User();
        u.setFullName(body.fullName());
        u.setEmail(User.normalizeEmail(body.email()));
        u.setPassword(passwordEncoder.encode(body.password()));

        User createdUser = userRepository.save(u);
        return RegisterResponseDTO.from(createdUser);
    }

    public TokenPair loginUser(LoginRequestDTO body) {
        User user = userRepository.findByEmail(User.normalizeEmail(body.email()))
                .orElseThrow(() -> new NotFoundException("User not found with the email"));
        if (!passwordEncoder.matches(user.getPassword(), body.password())) {
            throw new BadRequestException("Invalid credentials");
        }
        return tokenService.issueToken(user);
    }
}
