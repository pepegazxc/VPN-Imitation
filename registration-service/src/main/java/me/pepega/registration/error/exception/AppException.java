package me.pepega.registration.error.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class AppException extends RuntimeException {
    private HttpStatus status;
    private String message;
    private String code;

    protected AppException(HttpStatus status, String message, String code) {
        super(message);
        this.status = status;
        this.code = code;
    }

    protected AppException(HttpStatus status, String message, String code, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.code = code;
    }

}
