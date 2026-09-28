package me.pepega.registration.user.service.application;

import jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.user.dto.request.RegistrationRequest;
import me.pepega.registration.user.dto.response.SuccessionRegistrationResponse;
import me.pepega.registration.user.entity.AuthProvider;
import me.pepega.registration.user.entity.UsersEntity;
import me.pepega.registration.user.redis.orm.RedisTokenResult;
import me.pepega.registration.user.redis.orm.RedisTokenService;
import me.pepega.registration.user.repository.AuthRepository;
import me.pepega.registration.security.FieldEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final FieldEncryptor encryptor;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final RedisTokenService redisTokenService;

    @Transactional
    public SuccessionRegistrationResponse userRegistration(RegistrationRequest request){
        /*
        TO DO:
        1. Add logs
        2. Add unit tests
        3. Add mail sender
        4. Add checking on unique fields and move it to private methods
         */

        UsersEntity user = UsersEntity.builder()
                .username(request.getUsername())
                .cipherPhoneNumber(encryptor.encrypt(request.getPhoneNumber()))
                .cipherEmail(encryptor.encrypt(request.getEmail()))
                .hashPassword(encoder.encode(request.getPassword()))
                .createdAt(Instant.now())
                .authProvider(AuthProvider.LOCAL)
                .build();

        authRepository.save(user);


        Long userId = user.getId();

        String jwtToken = jwtProvider.generateToken(
                userId.toString(),
                "USER",
                Duration.ofMinutes(15)
        );

        RedisTokenResult token =  redisTokenService.generateRefreshToken(
                userId
        );

        return new SuccessionRegistrationResponse(
                "You have registered successfully!",
                token.rawToken(),
                jwtToken
        );
    }
}
