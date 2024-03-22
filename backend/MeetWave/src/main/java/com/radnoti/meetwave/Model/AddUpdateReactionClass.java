package com.radnoti.meetwave.Model;

public class AddUpdateReactionClass {

    int commentIDIN;

    int eventIDIN;

    int userIDIN;

    int reactionTypeIN;

    public AddUpdateReactionClass(int commentIDIN, int eventIDIN, int userIDIN, int reactionTypeIN) {
        this.commentIDIN = commentIDIN;
        this.eventIDIN = eventIDIN;
        this.userIDIN = userIDIN;
        this.reactionTypeIN = reactionTypeIN;
    }

    public int getCommentIDIN() {
        return commentIDIN;
    }

    public void setCommentIDIN(int commentIDIN) {
        this.commentIDIN = commentIDIN;
    }

    public int getEventIDIN() {
        return eventIDIN;
    }

    public void setEventIDIN(int eventIDIN) {
        this.eventIDIN = eventIDIN;
    }

    public int getUserIDIN() {
        return userIDIN;
    }

    public void setUserIDIN(int userIDIN) {
        this.userIDIN = userIDIN;
    }

    public int getReactionTypeIN() {
        return reactionTypeIN;
    }

    public void setReactionTypeIN(int reactionTypeIN) {
        this.reactionTypeIN = reactionTypeIN;
    }
}
