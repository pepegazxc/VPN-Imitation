package me.pepega.registration.user.service.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.user.dto.RegistrationRequest;
import me.pepega.registration.user.entity.AuthProvider;
import me.pepega.registration.user.entity.UsersEntity;
import me.pepega.registration.user.repository.AuthRepository;
import me.pepega.registration.security.FieldEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final FieldEncryptor encryptor;

    @Transactional
    public void userRegistration(RegistrationRequest request){
        /*
        TO DO:
        1. Create JWT token class for generating JWT tokens
        2. Add logs
         */

        UsersEntity user = UsersEntity.builder()
                .username("username")
                .cipherPhoneNumber(encryptor.encrypt(request.getPhoneNumber()))
                .cipherEmail(encryptor.encrypt(request.getEmail()))
                .hashPassword("hashPassword")
                .token("jwt Token")
                .createdAt(Instant.from(LocalDateTime.now()))
                .authProvider(AuthProvider.LOCAL)
                .build();

        authRepository.save(user);

    }
}
