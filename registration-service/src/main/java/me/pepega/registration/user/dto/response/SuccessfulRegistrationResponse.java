package me.pepega.registration.user.dto.response;

public record SuccessfulRegistrationResponse(
        String message,
        String jwtRefreshToken,
        String jwtAccessToken
) {
}
