package com.semicolon.africa.exceptions;

public class OldPasswordIncorrectException extends RuntimeException {
    public OldPasswordIncorrectException(String message) {
        super(message);
    }
}
