package com.radnoti.meetwave.Model;

public class changeUserPermissionClass {

    int userID;

    Boolean isAdminIN;

    public changeUserPermissionClass(int userID, Boolean isAdminIN) {
        this.userID = userID;
        this.isAdminIN = isAdminIN;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Boolean getAdminIN() {
        return isAdminIN;
    }

    public void setAdminIN(Boolean adminIN) {
        isAdminIN = adminIN;
    }
}
