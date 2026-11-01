package amitab.drive.contollers;

import amitab.drive.dto.*;
import amitab.drive.entity.User;
import amitab.drive.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final static String ACCESS_TOKEN_NAME = "drive_access_token";
    private final static String REFRESH_TOKEN_NAME = "drive_refresh_token";
    private final AuthService authService;

    @PostMapping("/")
    public ResponseEntity<RegisterResponseDTO> registerUser(
            @RequestBody RegisterRequestDTO body
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(body));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(
            @RequestBody LoginRequestDTO body,
            HttpServletResponse res
    ) {
        TokenPair tokens = authService.loginUser(body);
        setTokenCookies(res, tokens);
        return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseDTO(tokens.accessToken()));
    }

    private void setTokenCookies(HttpServletResponse res, TokenPair tokens) {
        ResponseCookie accessCookie = setCookies(ACCESS_TOKEN_NAME, tokens.accessToken(), 15);
        ResponseCookie refreshCookie = setCookies(REFRESH_TOKEN_NAME, tokens.refreshToken(), 43200);
        res.addHeader("Set-Cookie", accessCookie.toString());
        res.addHeader("Set-Cookie", refreshCookie.toString());
    }

    private ResponseCookie setCookies(String name, String value, long durationInMin) {
        return ResponseCookie
                .from(name, value)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .sameSite("lax")
                .maxAge(Duration.ofMinutes(durationInMin))
                .build();
    }
}
