package com.demo.configservice.exception;


public class ConfigNotFoundException extends RuntimeException {
    public ConfigNotFoundException(String message) {
        super(message);
    }
}

