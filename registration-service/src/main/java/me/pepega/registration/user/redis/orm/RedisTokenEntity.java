package me.pepega.registration.user.redis.orm;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "refresh_tokens", timeToLive = RedisTokenEntity.TTL_SECONDS)
public class RedisTokenEntity {

    public static final long TTL_SECONDS = 30L * 24 * 60 * 60;

    @Id
    private String tokenHash;

    private Long userid;

}
