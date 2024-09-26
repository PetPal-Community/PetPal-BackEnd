package com.ingsw.Petpal.exception;


public class DuplicatePaymentException extends BadRequestException {
  public DuplicatePaymentException(String message) {
    super(message);
  }
}

