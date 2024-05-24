package org.sopt.practice.common;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.sopt.practice.common.dto.ErrorResponse;
import org.sopt.practice.exception.ForbiddenError;
import org.sopt.practice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(final NotFoundException e) {
        log.error("Handle Entity Not Found Exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(e.getErrorMessage()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        log.error("Handle Method Argument Not Valid Exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(HttpStatus.BAD_REQUEST.value(), Objects.requireNonNull(
                        e.getBindingResult().getFieldError()).getDefaultMessage()));
    }

    @ExceptionHandler(value = {ForbiddenError.class})
    protected ResponseEntity<ErrorResponse> handleForbiddenError(final ForbiddenError e) {
        log.error("Handle Forbidden Error: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of(e.getErrorMessage()));
    }
}
