package me.pepega.registration.user.dto.response;

public record SuccessionRegistrationResponse(
        String message,
        String jwtRefreshToken,
        String jwtAccessToken
) {
}
