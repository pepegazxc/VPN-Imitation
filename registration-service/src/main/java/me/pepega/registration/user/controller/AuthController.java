package me.pepega.registration.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.pepega.registration.user.dto.RegistrationRequest;
import me.pepega.registration.user.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody RegistrationRequest request){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
