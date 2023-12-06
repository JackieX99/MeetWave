package net.meetwave.meetwaverest.Classes;

public class changeUserPermissionClass {

    int userID;

    boolean isAdminIN;

    public changeUserPermissionClass(int userID, boolean isAdminIN) {
        this.userID = userID;
        this.isAdminIN = isAdminIN;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isAdminIN() {
        return isAdminIN;
    }

    public void setAdminIN(boolean adminIN) {
        isAdminIN = adminIN;
    }
}
