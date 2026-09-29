package me.pepega.registration;

import jwt.JwtProvider;
import me.pepega.registration.error.exception.user.InvalidPhoneNumber;
import me.pepega.registration.error.exception.user.PhoneNumberException;
import me.pepega.registration.error.exception.user.UsernameAlreadyExist;
import me.pepega.registration.security.FieldEncryptor;
import me.pepega.registration.user.dto.request.RegistrationRequest;
import me.pepega.registration.user.entity.UsersEntity;
import me.pepega.registration.user.redis.orm.RedisTokenResult;
import me.pepega.registration.user.redis.orm.RedisTokenService;
import me.pepega.registration.user.repository.AuthRepository;
import me.pepega.registration.user.service.application.AuthService;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("registration: successful registration with creating two tokens")
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

    @Test
    @DisplayName("registration: registration method should return PhoneNumberException")
    public void shouldReturnPhoneNumberException(){
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("test");
        request.setPassword("test");
        request.setUsername("test");
        request.setPhoneNumber("InvalidFormat");

        when(encryptor.encrypt(anyString())).thenReturn("cipher");

        assertThrows(PhoneNumberException.class,
                () -> authService.userRegistration(request));

        verify(authRepository, never()).save(any());
    }

    @Test
    @DisplayName("registration: registration method should return InvalidPhoneNumberFormat")
    public void shouldReturnInvalidPhoneNumberFormat(){
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("test");
        request.setPassword("test");
        request.setUsername("test");
        request.setPhoneNumber("+77812397"); //random numbers with

        when(encryptor.encrypt(anyString())).thenReturn("cipher");

        assertThrows(InvalidPhoneNumber.class,
                () -> authService.userRegistration(request));

        verify(authRepository, never()).save(any());
    }

    @Test
    @DisplayName("registration: registration method should return UsernameAlreadyExist")
    public void shouldReturnUsernameAlreadyExist(){
        RegistrationRequest request = new RegistrationRequest();
        request.setEmail("test");
        request.setPassword("test");
        request.setUsername("test");
        request.setPhoneNumber("+79999999999");

        when(encryptor.encrypt(anyString())).thenReturn("cipher");
        when(authRepository.existsByUsername(anyString())).thenReturn(true);

        assertThrows(UsernameAlreadyExist.class,
                () -> authService.userRegistration(request));

        verify(authRepository, never()).save(any());
    }
}