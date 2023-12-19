package com.radnoti.meetwave.Model;

public class updateCommentClass {

    int userCommentID;

    String userCommentIN;

    public updateCommentClass(int userCommentID, String userCommentIN) {
        this.userCommentID = userCommentID;
        this.userCommentIN = userCommentIN;
    }

    public int getUserCommentID() {
        return userCommentID;
    }

    public void setUserCommentID(int userCommentID) {
        this.userCommentID = userCommentID;
    }

    public String getUserCommentIN() {
        return userCommentIN;
    }

    public void setUserCommentIN(String userCommentIN) {
        this.userCommentIN = userCommentIN;
    }
}
