package com.radnoti.meetwave.Model;

public class updateUserClass {

    Integer userID;

    String newFullName;

    String newEmail;

    String newPhoneNumber;

    public updateUserClass(Integer userID, String newFullName, String newEmail, String newPhoneNumber) {
        this.userID = userID;
        this.newFullName = newFullName;
        this.newEmail = newEmail;
        this.newPhoneNumber = newPhoneNumber;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getNewFullName() {
        return newFullName;
    }

    public void setNewFullName(String newFullName) {
        this.newFullName = newFullName;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }


}
