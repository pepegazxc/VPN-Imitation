package me.pepega.registration.user.redis.orm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("refresh_tokens")
public class RedisTokenEntity {

    @Id
    private String tokenHash;

    private Long userid;

    @TimeToLive(unit = TimeUnit.DAYS)
    private Long ttl = 30L;
}
