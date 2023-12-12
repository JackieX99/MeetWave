package net.meetwave.meetwaverest.Classes;

public class profilePictureUploadClass {

    int userID;

    String path;

    public profilePictureUploadClass(int userID, String path) {
        this.userID = userID;
        this.path = path;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
