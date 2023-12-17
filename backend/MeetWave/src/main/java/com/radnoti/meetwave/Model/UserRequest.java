package com.radnoti.meetwave.Model;

public class UserRequest {
    private Long userId;
    private String name;

    // Getterek, setterek

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
