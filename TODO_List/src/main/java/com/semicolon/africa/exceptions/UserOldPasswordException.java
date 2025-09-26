package com.semicolon.africa.exceptions;

public class UserOldPasswordException extends RuntimeException {
    public UserOldPasswordException(String message) {
        super(message);
    }
}
