package com.ontime.crrs.business.security.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException(String message) {
        super(message);
    }

    public PasswordMismatchException() {
        super("Passwords do not match! Please try again.");
    }

}