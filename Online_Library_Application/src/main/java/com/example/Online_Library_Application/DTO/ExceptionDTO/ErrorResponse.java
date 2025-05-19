package com.example.Online_Library_Application.DTO.ExceptionDTO;

public class ErrorResponse {
    private String error;
    private int status;
    private String timestamp;

    // Constructor
    public ErrorResponse(String error, int status) {
        this.error = error;
        this.status = status;
        this.timestamp = String.valueOf(System.currentTimeMillis()); // You can format this as needed
    }

    // Getters and Setters
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
