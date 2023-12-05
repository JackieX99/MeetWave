package net.meetwave.meetwaverest.Classes;

public class deleteCommentClass {

    int commentID;

    public deleteCommentClass(int commentID) {
        this.commentID = commentID;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}
