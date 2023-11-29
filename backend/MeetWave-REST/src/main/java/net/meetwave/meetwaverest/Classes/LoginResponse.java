package net.meetwave.meetwaverest.Classes;

public class LoginResponse {


    String status;

    public LoginResponse(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
