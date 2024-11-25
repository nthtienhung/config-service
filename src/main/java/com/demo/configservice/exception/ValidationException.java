package com.demo.configservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationException extends RuntimeException {
    private final String code;
    private final HttpStatus status;

    public ValidationException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }
}

