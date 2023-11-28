package net.meetwave.meetwaverest.Classes;

public class RegisterResponse {

    String status;

    public RegisterResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
