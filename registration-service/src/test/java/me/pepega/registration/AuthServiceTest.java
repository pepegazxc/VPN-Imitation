package me.pepega.registration;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jwt.JwtProvider;
import me.pepega.registration.security.FieldEncryptor;
import me.pepega.registration.user.dto.request.RegistrationRequest;
import me.pepega.registration.user.entity.UsersEntity;
import me.pepega.registration.user.redis.orm.RedisTokenResult;
import me.pepega.registration.user.redis.orm.RedisTokenService;
import me.pepega.registration.user.repository.AuthRepository;
import me.pepega.registration.user.service.application.AuthService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AuthRepository authRepository;
    @Mock
    private FieldEncryptor encryptor;
    @Mock
    private PasswordEncoder encoder;
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private RedisTokenService redisTokenService;

    @InjectMocks
    AuthService authService;

    @Test
    public void shouldRegisterUserSuccessfully() {
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("test");
        request.setPassword("test");
        request.setUsername("test");
        request.setPhoneNumber("+79999999999");

        when(encryptor.encrypt(anyString())).thenReturn("cipher");
        when(encoder.encode(anyString())).thenReturn("hash");
        when(authRepository.save(any(UsersEntity.class))).thenAnswer(ans -> {
            UsersEntity user = ans.getArgument(0);
            user.setId(1L);
            return user;
        });
        when(jwtProvider.generateToken(anyString(), anyString(), any())).thenReturn("jwt");
        when(redisTokenService.generateRefreshToken(1L)).thenReturn(new RedisTokenResult("raw", 1L));

        var response = authService.userRegistration(request);

        assertEquals("jwt", response.jwtAccessToken());
        assertEquals("raw", response.jwtRefreshToken());
        verify(redisTokenService).generateRefreshToken(1L);
    }
}