package net.meetwave.meetwaverest.Classes;

import java.util.Date;

public class postCommentClass {

    int eventID;

    int userID;

    String userCommentIN;

    java.util.Date dateOfCommentIN;

    public postCommentClass(int eventID, int userID, String userCommentIN, java.util.Date dateOfCommentIN) {
        this.eventID = eventID;
        this.userID = userID;
        this.userCommentIN = userCommentIN;
        this.dateOfCommentIN = dateOfCommentIN;
    }


    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserCommentIN() {
        return userCommentIN;
    }

    public void setUserCommentIN(String userCommentIN) {
        this.userCommentIN = userCommentIN;
    }

    public java.util.Date getDateOfCommentIN() {
        return dateOfCommentIN;
    }

    public void setDateOfCommentIN(java.util.Date dateOfCommentIN) {
        this.dateOfCommentIN = dateOfCommentIN;
    }



}
