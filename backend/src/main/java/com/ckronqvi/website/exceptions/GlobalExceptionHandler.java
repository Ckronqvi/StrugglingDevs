package com.ckronqvi.website.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(
            ResourceNotFoundException ex, 
            WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWasNotAuthenticatedException.class)
    public ResponseEntity<ApiError> handleUserWasNotAuthenticatedException(
            UserWasNotAuthenticatedException ex, 
            WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.UNAUTHORIZED.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SenderNotFoundException.class)
    public ResponseEntity<ApiError> handleSenderNotFoundExcpetion(
            SenderNotFoundException ex, 
            WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReceiverNotFoundException.class)
    public ResponseEntity<ApiError> handleRecieverNotFoundException(
            ReceiverNotFoundException ex, 
            WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameOrEmailAlreadyExistException.class)
    public ResponseEntity<ApiError> handleUsernameOrEmailAlreadyExistException(
            UsernameOrEmailAlreadyExistException ex, 
            WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.BAD_REQUEST.value(),
            ex.getMessage(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationExceptions(MethodArgumentNotValidException ex) {
        return "Invalid input";
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadCredentialsException(BadCredentialsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorizedException(UnauthorizedException ex) {
        return ex.getMessage();
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtException(
            Exception ex, 
            WebRequest request) {
        ApiError apiError = new ApiError(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "An unexpected error occurred",
            request.getDescription(false)
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
