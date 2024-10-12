package com.ingsw.petpal.exception;

public class DuplicateCommunityException extends RuntimeException {
    public DuplicateCommunityException() {
        super();
    }

    public DuplicateCommunityException(String message) {
        super(message);
    }
}
