package me.pepega.registration.user.redis.orm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisTokenService {

    private final RedisTokenRepository tokenRepository;

    public RedisTokenResult generateRefreshToken(Long userId){
        String token = UUID.randomUUID().toString();
        String tokenHash = DigestUtils.sha256Hex(token);

        RedisTokenEntity entity = new RedisTokenEntity(tokenHash, userId);
        tokenRepository.save(entity);
        log.info("Created refresh token for user={}", userId);

        return new RedisTokenResult(token, userId);
    }
}
