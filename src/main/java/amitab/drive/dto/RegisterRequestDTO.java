package amitab.drive.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "Full name is required")
        String fullName,

        @Email(message = "Valid email is required")
        @NotBlank
        String email,

        @Size(min = 6, message = "Password must be 6 character long")
        @NotBlank
        String password
) {
}
