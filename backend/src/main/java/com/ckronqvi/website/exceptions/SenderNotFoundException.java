package com.ckronqvi.website.exceptions;

public class SenderNotFoundException extends RuntimeException {
    public SenderNotFoundException(String message) {
        super(message);
    }

    public SenderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
