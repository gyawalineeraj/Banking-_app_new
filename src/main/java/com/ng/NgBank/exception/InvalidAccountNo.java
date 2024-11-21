package com.ng.NgBank.exception;

public class InvalidAccountNo extends RuntimeException {
    public InvalidAccountNo(String message) {
        super(message);
    }
}
