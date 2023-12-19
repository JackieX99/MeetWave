package com.radnoti.meetwave.Model;

public class changePasswordClass {

    int userID;

    String newPassword;

    public changePasswordClass(int userID, String newPassword) {
        this.userID = userID;
        this.newPassword = newPassword;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
