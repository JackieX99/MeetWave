package net.meetwave.meetwaverest.Classes;

public class updateUserClass {

    int userID;

    String newFullName;

    String newEmail;

    String newPhoneNumber;



    public updateUserClass(int userID, String newFullName, String newEmail, String newPhoneNumber) {
        this.userID = userID;
        this.newFullName = newFullName;
        this.newEmail = newEmail;
        this.newPhoneNumber = newPhoneNumber;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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
