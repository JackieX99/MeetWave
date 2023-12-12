package net.meetwave.meetwaverest.Classes;

public class userParticipateOnEventClass {

    int eventIDIN;

    int userIDIN;

    int typeOfParticipationIN;

    public userParticipateOnEventClass(int eventIDIN, int userIDIN, int typeOfParticipationIN) {
        this.eventIDIN = eventIDIN;
        this.userIDIN = userIDIN;
        this.typeOfParticipationIN = typeOfParticipationIN;
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

    public int getTypeOfParticipationIN() {
        return typeOfParticipationIN;
    }

    public void setTypeOfParticipationIN(int typeOfParticipationIN) {
        this.typeOfParticipationIN = typeOfParticipationIN;
    }
}
