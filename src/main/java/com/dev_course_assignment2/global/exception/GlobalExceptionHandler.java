package com.dev_course_assignment2.global.exception;

import com.dev_course_assignment2.global.rsData.RsData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RsData<Void>> handleValidationException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("요청 값이 올바르지 않습니다.");

        return badRequest("400-1", message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RsData<Void>> handleIllegalArgumentException(IllegalArgumentException exception) {
        return badRequest("400-2", exception.getMessage());
    }

    private ResponseEntity<RsData<Void>> badRequest(String resultCode, String message) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new RsData<>(resultCode, message));
    }
}
