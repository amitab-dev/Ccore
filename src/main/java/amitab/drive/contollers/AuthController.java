package amitab.drive.contollers;

import amitab.drive.dto.RegisterRequestDTO;
import amitab.drive.dto.RegisterResponseDTO;
import amitab.drive.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/")
    public ResponseEntity<RegisterResponseDTO> registerUser(
            @RequestBody RegisterRequestDTO body
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(body));
    }
}
