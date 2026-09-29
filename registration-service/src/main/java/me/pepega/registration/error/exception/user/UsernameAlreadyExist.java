package me.pepega.registration.error.exception.user;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyExist extends AppException {
    public UsernameAlreadyExist() {
        super(
                HttpStatus.CONFLICT,
                "Username already exist. Try another one",
                "USERNAME_ALREADY_TAKEN"
        );
    }
}
