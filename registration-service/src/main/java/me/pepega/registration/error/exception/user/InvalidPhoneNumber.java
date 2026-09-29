package me.pepega.registration.error.exception.user;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class InvalidPhoneNumber extends AppException {
    public InvalidPhoneNumber() {
        super(
                HttpStatus.CONFLICT,
                "Invalid phone number format",
                "INVALID_PHONE_NUMBER_FORMAT"
        );
    }
}
