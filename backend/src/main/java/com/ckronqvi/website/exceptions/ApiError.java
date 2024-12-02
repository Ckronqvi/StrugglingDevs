package com.ckronqvi.website.exceptions;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;
    
    public ApiError(int status, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
