package com.radnoti.meetwave.Model;

public class subscriptionLogClass {

    int userIDIN;

    Boolean statusIN;

    int typeOfSubscriptionIN;

    public subscriptionLogClass(int userIDIN, Boolean statusIN, int typeOfSubscriptionIN) {
        this.userIDIN = userIDIN;
        this.statusIN = statusIN;
        this.typeOfSubscriptionIN = typeOfSubscriptionIN;
    }


    public int getUserIDIN() {
        return userIDIN;
    }

    public void setUserIDIN(int userIDIN) {
        this.userIDIN = userIDIN;
    }

    public Boolean getStatusIN() {
        return statusIN;
    }

    public void setStatusIN(Boolean statusIN) {
        this.statusIN = statusIN;
    }

    public int getTypeOfSubscriptionIN() {
        return typeOfSubscriptionIN;
    }

    public void setTypeOfSubscriptionIN(int typeOfSubscriptionIN) {
        this.typeOfSubscriptionIN = typeOfSubscriptionIN;
    }
}
