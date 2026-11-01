package amitab.drive.services;

import amitab.drive.dto.TokenPair;
import amitab.drive.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public TokenPair issueToken(User user) {
        String accessToken = jwtService.issueAccessToken(user);
        String refreshToken = refreshTokenService.issueRefreshToken();
        return new TokenPair(accessToken, refreshToken);
    }
}
