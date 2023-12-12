package net.meetwave.meetwaverest.Classes;

public class setSubscriptionClass {

    int userID;

    int subscriptionTypeIN;

    public setSubscriptionClass(int userID, int subscriptionTypeIN) {
        this.userID = userID;
        this.subscriptionTypeIN = subscriptionTypeIN;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSubscriptionTypeIN() {
        return subscriptionTypeIN;
    }

    public void setSubscriptionTypeIN(int subscriptionTypeIN) {
        this.subscriptionTypeIN = subscriptionTypeIN;
    }
}
