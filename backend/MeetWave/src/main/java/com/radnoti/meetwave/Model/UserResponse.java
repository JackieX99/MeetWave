package com.radnoti.meetwave.Model;

public class UserResponse {
    private String message;

    public UserResponse() {
    }

    public UserResponse(String message) {
        this.message = message;
    }

    // Getter, setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
