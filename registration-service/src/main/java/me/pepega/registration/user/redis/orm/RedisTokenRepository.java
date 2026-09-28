package me.pepega.registration.user.redis.orm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisTokenEntity, String> {
}
