package com.ingsw.petpal.exception;

public class RolNotFoundException extends RuntimeException {
    public RolNotFoundException(){super();}
    public RolNotFoundException(String message) {
        super(message);
    }
}
