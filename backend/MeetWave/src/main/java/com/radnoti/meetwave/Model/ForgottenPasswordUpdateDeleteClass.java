package com.radnoti.meetwave.Model;

public class ForgottenPasswordUpdateDeleteClass {

    String emailIN;

    String token;

    String newPassword;

    public ForgottenPasswordUpdateDeleteClass(String emailIN, String token, String newPassword) {
        this.emailIN = emailIN;
        this.token = token;
        this.newPassword = newPassword;
    }

    public String getEmailIN() {
        return emailIN;
    }

    public void setEmailIN(String emailIN) {
        this.emailIN = emailIN;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
