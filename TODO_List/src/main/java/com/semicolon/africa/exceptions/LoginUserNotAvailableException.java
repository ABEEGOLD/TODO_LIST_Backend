package com.semicolon.africa.exceptions;

public class LoginUserNotAvailableException extends RuntimeException {
    public LoginUserNotAvailableException(String message) {
        super(message);
    }
}
