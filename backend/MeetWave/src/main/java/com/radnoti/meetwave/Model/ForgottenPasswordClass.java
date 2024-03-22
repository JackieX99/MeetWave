package com.radnoti.meetwave.Model;

public class ForgottenPasswordClass {

    String emailIN;

    String randomString;

    public ForgottenPasswordClass(String emailIN, String randomString) {
        this.emailIN = emailIN;
        this.randomString = randomString;
    }

    public String getEmailIN() {
        return emailIN;
    }

    public void setEmailIN(String emailIN) {
        this.emailIN = emailIN;
    }

    public String randomString() {
        return randomString;
    }

    public void randomString(String randomString) {
        this.randomString = randomString;
    }
}
