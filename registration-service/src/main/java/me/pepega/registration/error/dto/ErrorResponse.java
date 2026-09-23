package me.pepega.registration.error.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        Instant timestamp,
        List<String> details
) {

    public static ErrorResponse of(String code, String message){
        return new ErrorResponse(code, message, Instant.now(), List.of());
    }

    public static ErrorResponse of(String code, String message, List<String> details){
        return new ErrorResponse(code, message, Instant.now(), details);
    }
}
