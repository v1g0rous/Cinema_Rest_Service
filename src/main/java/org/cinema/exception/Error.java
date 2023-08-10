package org.cinema.exception;

import java.time.LocalDateTime;

public class Error {
    private int statusCode;
    private LocalDateTime timestamp;
    private String description;
    private String error;

    public Error(String error) {
        this.error = error;
    }

    public Error(int statusCode, LocalDateTime timestamp, String description, String error) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.description = description;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
