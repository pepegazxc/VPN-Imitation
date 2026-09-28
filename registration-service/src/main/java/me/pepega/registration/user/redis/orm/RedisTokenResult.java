package me.pepega.registration.user.redis.orm;

public record RedisTokenResult(
        String rawToken,
        Long userId,
        String userSession
) {
}
