package com.ckronqvi.website.exceptions;

public class UserWasNotAuthenticatedException extends RuntimeException {
    public UserWasNotAuthenticatedException(String message) {
        super(message);
    }

    public UserWasNotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
