package com.demo.configservice.exception;

import com.demo.configservice.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<MessageResponse<Object>> handleValidationException(ValidationException ex) {
        MessageResponse<Object> response = MessageResponse.builder()
                .message(ex.getMessage())
                .status((short) ex.getStatus().value())
                .localDateTime(LocalDateTime.now().toString())
                .data(null)
                .build();
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        MessageResponse<Object> response = MessageResponse.builder()
                .message("Validation failed")
                .status((short) 400)
                .localDateTime(LocalDateTime.now().toString())
                .data(errors)
                .build();
        return ResponseEntity.badRequest().body(response);
    }
}
