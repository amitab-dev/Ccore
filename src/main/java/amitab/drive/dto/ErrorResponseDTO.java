package amitab.drive.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ErrorResponseDTO(
        HttpStatus status,
        String message,
        String path,
        Instant timestamp
) {
}
