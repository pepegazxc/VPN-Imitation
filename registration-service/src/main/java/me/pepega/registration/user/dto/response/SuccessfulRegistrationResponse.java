package me.pepega.registration.user.dto.response;

public record SuccessRegistrationResponse(
        String message,
        String jwtRefreshToken,
        String jwtAccessToken
) {
}
