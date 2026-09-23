package me.pepega.registration.error.advice;

import me.pepega.registration.error.dto.ErrorResponse;
import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    //TO DO : ADD LOGS

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> appExceptionHandler(AppException ex){
        return ResponseEntity.status(ex.getStatus())
                .body(
                        ErrorResponse.of(
                                ex.getCode(),
                                ex.getMessage()
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> appUnexpectedExceptionHandler(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.of(
                                "UNEXPECTED_ERROR",
                                "An unexpected error occurred. Please try again later."
                        )
                );
    }
}
