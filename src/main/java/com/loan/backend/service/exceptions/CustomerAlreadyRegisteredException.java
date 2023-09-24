package com.loan.backend.service.exceptions;

public class CustomerAlreadyRegisteredException extends RuntimeException {

    public CustomerAlreadyRegisteredException(String message) {
        super(message);
    }
}
