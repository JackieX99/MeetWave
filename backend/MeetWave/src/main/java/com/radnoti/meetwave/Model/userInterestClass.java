package com.radnoti.meetwave.Model;

public class userInterestClass {

    int userIDIN;

    String interestIN;

    public userInterestClass(int userIDIN, String interestIN) {
        this.userIDIN = userIDIN;
        this.interestIN = interestIN;
    }

    public int getUserIDIN() {
        return userIDIN;
    }

    public void setUserIDIN(int userIDIN) {
        this.userIDIN = userIDIN;
    }

    public String getInterestIN() {
        return interestIN;
    }

    public void setInterestIN(String interestIN) {
        this.interestIN = interestIN;
    }
}
