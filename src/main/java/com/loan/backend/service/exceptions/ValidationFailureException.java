package com.loan.backend.service.exceptions;

public class ValidationFailureException extends RuntimeException {

    public ValidationFailureException(final String message) {
        super(message);
    }
}
