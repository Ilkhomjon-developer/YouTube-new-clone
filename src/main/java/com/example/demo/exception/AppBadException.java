package com.example.demo.exception;

public class AppBadException extends RuntimeException {

    public AppBadException(String message) {
        super(message);
    }
}