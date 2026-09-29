package me.pepega.registration.error.exception.user;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class PhoneNumberAlreadyExist extends AppException {
    public PhoneNumberAlreadyExist() {
        super(
                HttpStatus.CONFLICT,
                "Phone number already exist. Try another one",
                "PHONE_NUMBERP_ALREADY_TAKEN"
        );
    }
}
