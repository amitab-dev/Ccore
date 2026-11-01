package amitab.drive.dto;

public record TokenPair(
        String accessToken,
        String refreshToken
) {
}
