package com.ng.NgBank;

public class RoleDoesNotExist extends RuntimeException {
    public RoleDoesNotExist(String message) {
        super(message);
    }
}
