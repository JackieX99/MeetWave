package net.meetwave.meetwaverest.Classes;

public class changePasswordClass {

    String userID;

    String newpassword;

    public changePasswordClass(String userID, String newpassword) {
        this.userID = userID;
        this.newpassword = newpassword;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
