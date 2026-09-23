package me.pepega.registration.error.advice;

import lombok.extern.slf4j.Slf4j;
import me.pepega.registration.error.dto.ErrorResponse;
import me.pepega.registration.error.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> appExceptionHandler(AppException ex){
        if (ex.getStatus().is5xxServerError()) {
            log.error("Internal app exception occurred: status={}, code={}, message={}",
                    ex.getStatus(), ex.getCode(), ex.getMessage(), ex);
        } else {
            log.warn("Client exception occurred: status={}, code={}, message={}",
                    ex.getStatus(), ex.getCode(), ex.getMessage());
        }
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
        log.error("Unexpected exception occurred: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.of(
                                "UNEXPECTED_ERROR",
                                "An unexpected error occurred. Please try again later."
                        )
                );
    }
}
