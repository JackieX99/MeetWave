package net.meetwave.meetwaverest.Classes;

import java.sql.Timestamp;
import java.util.Date;

public class updateEventClass {

    int eventID;

    String eventTitleIN;

    String descriptionIN;

    java.sql.Timestamp dateOfTheEventIN;

    String placeOfTheEventIN;

    String founderOfTheEventIN;

    int maxParticipantsIN;

    public updateEventClass(int eventID, String eventTitleIN, String descriptionIN, Timestamp dateOfTheEventIN, String placeOfTheEventIN, String founderOfTheEventIN, int maxParticipantsIN) {
        this.eventID = eventID;
        this.eventTitleIN = eventTitleIN;
        this.descriptionIN = descriptionIN;
        this.dateOfTheEventIN = dateOfTheEventIN;
        this.placeOfTheEventIN = placeOfTheEventIN;
        this.founderOfTheEventIN = founderOfTheEventIN;
        this.maxParticipantsIN = maxParticipantsIN;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventTitleIN() {
        return eventTitleIN;
    }

    public void setEventTitleIN(String eventTitleIN) {
        this.eventTitleIN = eventTitleIN;
    }

    public String getDescriptionIN() {
        return descriptionIN;
    }

    public void setDescriptionIN(String descriptionIN) {
        this.descriptionIN = descriptionIN;
    }

    public Timestamp getDateOfTheEventIN() {
        return dateOfTheEventIN;
    }

    public void setDateOfTheEventIN(Timestamp dateOfTheEventIN) {
        this.dateOfTheEventIN = dateOfTheEventIN;
    }

    public String getPlaceOfTheEventIN() {
        return placeOfTheEventIN;
    }

    public void setPlaceOfTheEventIN(String placeOfTheEventIN) {
        this.placeOfTheEventIN = placeOfTheEventIN;
    }

    public String getFounderOfTheEventIN() {
        return founderOfTheEventIN;
    }

    public void setFounderOfTheEventIN(String founderOfTheEventIN) {
        this.founderOfTheEventIN = founderOfTheEventIN;
    }

    public int getMaxParticipantsIN() {
        return maxParticipantsIN;
    }

    public void setMaxParticipantsIN(int maxParticipantsIN) {
        this.maxParticipantsIN = maxParticipantsIN;
    }
}
