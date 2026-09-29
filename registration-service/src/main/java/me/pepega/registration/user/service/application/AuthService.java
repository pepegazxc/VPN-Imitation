package me.pepega.registration.user.service.application;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.user.dto.request.RegistrationRequest;
import me.pepega.registration.user.dto.response.SuccessfulRegistrationResponse;
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
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private static final PhoneNumberUtil PHONE_UTIL = PhoneNumberUtil.getInstance();

    private final AuthRepository authRepository;
    private final FieldEncryptor encryptor;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final RedisTokenService redisTokenService;

            /*
        TO DO:
        1. Add logs
        2. Add unit tests
        3. Add mail sender
         */


    @Transactional
    public SuccessfulRegistrationResponse userRegistration(RegistrationRequest request){
        String username = request.getUsername().trim();
        String cipherEmail = encryptor.encrypt(normalizeEmail(request.getEmail()));
        String cipherPhoneNumber = encryptor.encrypt(normalizePhoneNumber(request.getPhoneNumber()));

        assertUnique(username, cipherEmail, cipherPhoneNumber);

        UsersEntity user = authRepository.save(UsersEntity.builder()
                .username(username)
                .cipherPhoneNumber(cipherPhoneNumber)
                .cipherEmail(cipherEmail)
                .hashPassword(encoder.encode(request.getPassword()))
                .createdAt(Instant.now())
                .authProvider(AuthProvider.LOCAL)
                .build());

        return issuedToken(user.getId());
    }

    private void assertUnique(String username, String cipherEmail, String cipherPhoneNumber){
        // Create custom exceptions and add them to the handler
        if(authRepository.existsByUsername(username)) throw new RuntimeException();
        if(authRepository.existsByCipherEmail(cipherEmail)) throw new RuntimeException();
        if(authRepository.existsByCipherPhoneNumber(cipherPhoneNumber)) throw new RuntimeException();
    }

    private SuccessfulRegistrationResponse issuedToken(Long userId){
        String jwtAccess = jwtProvider.generateToken(
                userId.toString(),
                "USER",
                Duration.ofMinutes(15)
        );

        RedisTokenResult token =  redisTokenService.generateRefreshToken(
                userId
        );

        return new SuccessfulRegistrationResponse(
                "You have registered successfully!",
                token.rawToken(),
                jwtAccess
        );
    }

    private String normalizeEmail(String email){
        return email.trim().toLowerCase(Locale.ROOT);
    }

    private String normalizePhoneNumber(String phoneNUmber){
        try{
            Phonenumber.PhoneNumber parsed = PHONE_UTIL.parse(phoneNUmber, null);
            if (!PHONE_UTIL.isValidNumber(parsed)){
                throw new RuntimeException();
            }
            return PHONE_UTIL.format(parsed, PhoneNumberUtil.PhoneNumberFormat.E164);
        }catch (NumberParseException e){
            throw new RuntimeException();
        }
    }
}
