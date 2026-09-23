package me.pepega.registration.error.exception.cryptography;

import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;

public class DecryptException extends AppException {
    public DecryptException() {
        super(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred while decrypting",
                "DECRYPT_EXCEPTION"
        );
    }
}
