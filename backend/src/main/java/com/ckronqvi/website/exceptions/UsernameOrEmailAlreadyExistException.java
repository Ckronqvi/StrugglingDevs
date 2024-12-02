package com.ckronqvi.website.exceptions;

public class UsernameOrEmailAlreadyExistException extends RuntimeException {
    public UsernameOrEmailAlreadyExistException(String message) {
        super(message);
    }

    public UsernameOrEmailAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
