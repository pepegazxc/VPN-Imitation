package me.pepega.registration.user.repository;

import me.pepega.registration.user.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UsersEntity, Long> {
}
