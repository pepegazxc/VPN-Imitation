package me.pepega.registration.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public abstract class AppException extends RuntimeException {
    private HttpStatus status;
    private String message;
    private String code;

}
