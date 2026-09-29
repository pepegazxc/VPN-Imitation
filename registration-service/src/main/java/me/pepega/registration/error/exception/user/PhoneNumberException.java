package me.pepega.registration.error.exception.user;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class PhoneNumberException extends AppException {
    public PhoneNumberException(Throwable cause) {
        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An exception occurred while validating phone number",
                "PHONE_VALIDATION_EXCEPTION",
                cause
        );
    }
}
