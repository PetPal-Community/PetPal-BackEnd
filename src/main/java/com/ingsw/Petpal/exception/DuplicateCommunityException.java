package com.ingsw.Petpal.exception;


public class DuplicateCommunityException extends RuntimeException {
    public DuplicateCommunityException() {
        super();
    }

    public DuplicateCommunityException(String message) {
        super(message);
    }
}
