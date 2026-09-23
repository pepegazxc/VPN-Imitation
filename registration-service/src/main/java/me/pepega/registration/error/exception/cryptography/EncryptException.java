package me.pepega.registration.error.exception.cryptography;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class EncryptException extends AppException {
    public EncryptException() {
        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred while encrypting",
                "ENCRYPT_SERVER_EXCEPTION"
        );
    }
}
