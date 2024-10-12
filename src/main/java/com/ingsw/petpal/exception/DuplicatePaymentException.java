package com.ingsw.petpal.exception;

public class DuplicatePaymentException extends BadRequestException {
    public DuplicatePaymentException(String message) {
        super(message);
    }
}