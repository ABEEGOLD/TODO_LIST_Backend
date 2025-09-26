package com.semicolon.africa.exceptions;

public class LoginUserNotAvaiableException extends RuntimeException {
    public LoginUserNotAvaiableException(String message) {
        super(message);
    }
}
