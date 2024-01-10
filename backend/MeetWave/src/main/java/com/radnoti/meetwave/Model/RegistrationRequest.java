package com.radnoti.meetwave.Model;

public class RegistrationRequest {
    private String fullNameIN;
    private String emailIN;
    private String passwordIN;
    private String phoneNumberIN;


    public RegistrationRequest(String fullNameIN, String emailIN, String passwordIN, String phoneNumberIN) {
        this.fullNameIN = fullNameIN;
        this.emailIN = emailIN;
        this.passwordIN = passwordIN;
        this.phoneNumberIN = phoneNumberIN;
    }

    public String getFullNameIN() {
        return fullNameIN;
    }

    public void setFullNameIN(String fullNameIN) {
        this.fullNameIN = fullNameIN;
    }

    public String getEmailIN() {
        return emailIN;
    }

    public void setEmailIN(String emailIN) {
        this.emailIN = emailIN;
    }

    public String getPasswordIN() {
        return passwordIN;
    }

    public void setPasswordIN(String passwordIN) {
        this.passwordIN = passwordIN;
    }

    public String getPhoneNumberIN() {
        return phoneNumberIN;
    }

    public void setPhoneNumberIN(String phoneNumberIN) {
        this.phoneNumberIN = phoneNumberIN;
    }
}

