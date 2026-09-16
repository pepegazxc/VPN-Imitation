package me.pepega.registration.user.service.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.user.dto.RegistrationRequest;
import me.pepega.registration.user.entity.AuthProvider;
import me.pepega.registration.user.entity.UsersEntity;
import me.pepega.registration.user.repository.AuthRepository;
import me.pepega.registration.user.service.infrastructure.FieldEncryptorService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final FieldEncryptorService encryptorService;

    @Transactional
    public void userRegistration(RegistrationRequest request){
        /*
        TO DO:
        1. Create Security class with PasswordEncoder bean for hashing
        2. Create JWT token class for generating JWT tokens
        3. Create Controller Advice class for handling an exception
         */

        UsersEntity user = UsersEntity.builder()
                .username("username")
                .cipherPhoneNumber(encryptorService.encrypt(request.getPhoneNumber()))
                .cipherEmail(encryptorService.encrypt(request.getEmail()))
                .hashPassword("hashPassword")
                .token("jwt Token")
                .createdAt(Instant.from(LocalDateTime.now()))
                .authProvider(AuthProvider.LOCAL)
                .build();

        authRepository.save(user);

    }
}
