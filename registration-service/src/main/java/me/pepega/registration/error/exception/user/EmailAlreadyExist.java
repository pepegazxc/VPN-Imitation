package me.pepega.registration.error.exception.user;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyExist extends AppException {
    public EmailAlreadyExist() {
        super(
                HttpStatus.CONFLICT,
                "Email already exist. Try another one",
                "EMAIL_ALREADY_TAKEN"
        );
    }
}
