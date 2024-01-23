package com.example.exp;

public class AppBadException extends RuntimeException {
    public AppBadException(String message) {
        super(message);
    }
}