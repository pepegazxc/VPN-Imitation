package me.pepega.registration.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.user.dto.RegistrationRequest;
import me.pepega.registration.user.entity.AuthProvider;
import me.pepega.registration.user.entity.UsersEntity;
import me.pepega.registration.user.repository.AuthRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    @Transactional
    public void userRegistration(RegistrationRequest request){
        /*
        TO DO:
        1. Create Cipher class with cipher methods
        2. Create Security class with PasswordEncoder bean for hashing
        3. Create JWT token class for generating JWT tokens
        4. Create Controller Advice class for handling an exception
         */

        UsersEntity user = UsersEntity.builder()
                .username("username")
                .cipherPhoneNumber("cipherPhoneNumber")
                .cipherEmail("cipherEmail")
                .hashPassword("hashPassword")
                .token("jwt Token")
                .createdAt(Instant.from(LocalDateTime.now()))
                .authProvider(AuthProvider.LOCAL)
                .build();

        authRepository.save(user);

    }
}
